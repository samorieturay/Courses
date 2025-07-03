import os
import pandas as pd
import matplotlib.pyplot as plt
import networkx as nx

def generate_connectome_image(scan_id, time_series_df, output_dir="./"):
    """
    Generate a structural connectome from a time series dataframe and save it as an image.

    Args:
        scan_id (str): ID of the scan.
        time_series_df (pd.DataFrame): Time series data of brain regions.
        output_dir (str): Directory to save the image.
    """
    # Step 1: Compute correlation matrix between regions
    corr_matrix = time_series_df.corr()

    # Step 2: Create a graph from the correlation matrix
    G = nx.Graph()
    num_nodes = corr_matrix.shape[0]

    for i in range(num_nodes):
        for j in range(i + 1, num_nodes):
            weight = corr_matrix.iloc[i, j]
            if abs(weight) > 0.5:  # Threshold to avoid clutter
                G.add_edge(i, j, weight=weight)

    # Step 3: Draw the graph
    plt.figure(figsize=(10, 10))
    pos = nx.spring_layout(G, seed=42)
    edges = G.edges()
    weights = [G[u][v]['weight'] for u, v in edges]

    nx.draw_networkx_nodes(G, pos, node_size=100, node_color="skyblue")
    nx.draw_networkx_edges(G, pos, edgelist=edges, width=[2 * abs(w) for w in weights], edge_color="gray", alpha=0.7)

    plt.title(f"Structural Connectome: {scan_id}", fontsize=14)
    plt.axis("off")

    # Step 4: Save the image
    image_path = os.path.join(output_dir, f"{scan_id}_connectome.png")
    plt.savefig(image_path, bbox_inches='tight')
    plt.close()
    return image_path

# === Main Execution ===

# Load your CSV files
file_paths = {
    "001_1": "0001_1_RestEmpBOLD.csv",
    "001_2": "0001_2_RestEmpBOLD.csv",
    "002_1": "0002_1_RestEmpBOLD.csv"
    # Add "002_2": "0002_2_RestEmpBOLD.csv" if available
}

# Process and generate connectome images
for scan_id, file_path in file_paths.items():
    df = pd.read_csv(file_path, header=None)
    generate_connectome_image(scan_id, df)

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import os
from scipy.stats import linregress
import seaborn as sns  

# Load the structural connectome count matrix (70x70)
counts_df = pd.read_csv(r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_2_Lengths.csv", header=None)

def plot_connectome_matrix(counts_df, normalize=True, cmap='jet'):
    matrix = counts_df.values.astype(float)

    # Normalize matrix values to range [0, 1] if requested
    if normalize:
        max_val = np.max(matrix)
        if max_val > 0:
            matrix = matrix / max_val

    # Plot the matrix as a heatmap
    plt.figure(figsize=(6, 6))
    plt.imshow(matrix, interpolation='nearest', cmap=cmap)
    plt.colorbar(label='Normalized Streamline Count' if normalize else 'Streamline Count')
    plt.title("Structural Connectome Matrix")
    plt.xlabel("Brain Region")
    plt.ylabel("Brain Region")
    plt.tight_layout()
    plt.show()

# Call the function on your data

# plot_connectome_matrix(counts_df, normalize=True)


def plot_multiple_connectomes(file_paths, mode='all', threshold=None, cmap='seismic'):
    """
    Plot multiple functional connectomes with individual colorbars in a 2x2 grid.

    Parameters:
    - file_paths: list of 4 paths to CSV files (correlation matrices)
    - mode: 'all', 'positive', or 'negative'
    - threshold: float or None — min absolute correlation to keep
    - cmap: matplotlib colormap (default='seismic' for red-blue)
    """
    fig, axs = plt.subplots(2, 2, figsize=(14, 12))
    axs = axs.flatten()

    for i, path in enumerate(file_paths):
        # Load matrix from CSV
        fc_df = pd.read_csv(path, header=None)
        matrix = fc_df.values.astype(float)

        # Filter by edge polarity
        if mode == 'positive':
            matrix = np.where(matrix > 0, matrix, 0)
        elif mode == 'negative':
            matrix = np.where(matrix < 0, matrix, 0)

        # Apply threshold if specified
        if threshold is not None:
            matrix = np.where(np.abs(matrix) >= threshold, matrix, 0)

        # Plot matrix
        im = axs[i].imshow(matrix, interpolation='nearest', cmap=cmap, vmin=-1, vmax=1)
        filename = os.path.basename(path)
        axs[i].set_title(f"{filename}\n(mode={mode}, threshold={threshold})", fontsize=10)
        axs[i].set_xlabel("Brain Region")
        axs[i].set_ylabel("Brain Region")

        # Add individual colorbar
        cbar = fig.colorbar(im, ax=axs[i], fraction=0.046, pad=0.02)
        cbar.set_label("Correlation")

    plt.tight_layout()
    plt.show()

# 👇 List your 4 functional connectome file paths
file_paths = [
    r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_RestEmpCorrFC.csv",
    r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_2_RestEmpCorrFC.csv",
    r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_1_RestEmpCorrFC.csv",
    r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_2_RestEmpCorrFC.csv"
]

# ✅ Run the visualization
# plot_multiple_connectomes(file_paths, mode='all', threshold=None)


# def plot_histogram_grid(data_list, titles, xlabel, ylabel="# of connections", bins=50, color='mediumorchid', grid_shape=(2, 2), suptitle=""):
#     """
#     Plot multiple histograms in a grid layout.
#     """
#     fig, axs = plt.subplots(*grid_shape, figsize=(16, 10))
#     axs = axs.flatten()

#     for i, data in enumerate(data_list):
#         axs[i].hist(data, bins=bins, color=color, edgecolor='black')
#         axs[i].set_title(titles[i], fontsize=10)
#         axs[i].set_xlabel(xlabel)
#         axs[i].set_ylabel(ylabel)
#         axs[i].grid(True, linestyle='--', alpha=0.5)

#     for j in range(len(data_list), len(axs)):
#         axs[j].axis('off')  # hide unused subplots

#     plt.suptitle(suptitle, fontsize=14)
#     plt.tight_layout(rect=[0, 0, 1, 0.96])
#     plt.show()

# # ----------- Structural Connectomes -----------

# structural_paths = [
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_Counts.csv",
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_2_Counts.csv",
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_1_Counts.csv",
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_2_Counts.csv"
# ]

# struct_data = []
# struct_titles = []

# for path in structural_paths:
#     mat = pd.read_csv(path, header=None).values
#     upper_tri = mat[np.triu_indices_from(mat, k=1)]
#     struct_data.append(upper_tri)
#     struct_titles.append(os.path.basename(path))

#  # plot_histogram_grid(struct_data, struct_titles, xlabel="Streamline Count", suptitle="Structural Connectome Histograms")

# # ----------- Functional Connectomes (All, Pos, Neg) -----------

# functional_paths = [
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_RestEmpCorrFC.csv",
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_2_RestEmpCorrFC.csv",
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_1_RestEmpCorrFC.csv",
#     r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_2_RestEmpCorrFC.csv"
# ]

# def extract_fc_sets(paths, filter_func):
#     data = []
#     labels = []
#     for path in paths:
#         mat = pd.read_csv(path, header=None).values
#         values = mat[~np.eye(mat.shape[0], dtype=bool)]
#         filtered = filter_func(values)
#         data.append(filtered)
#         labels.append(os.path.basename(path))
#     return data, labels

# # Functional: All
# fc_all_data, fc_all_titles = extract_fc_sets(functional_paths, lambda x: x)
# plot_histogram_grid(fc_all_data, fc_all_titles, xlabel="Correlation", suptitle="Functional Connectome (All Edges)")

# # Functional: Positive
# fc_pos_data, fc_pos_titles = extract_fc_sets(functional_paths, lambda x: x[x > 0])
# plot_histogram_grid(fc_pos_data, fc_pos_titles, xlabel="Correlation", suptitle="Functional Connectome (Positive Edges)")

# # Functional: Negative
# fc_neg_data, fc_neg_titles = extract_fc_sets(functional_paths, lambda x: x[x < 0])
# plot_histogram_grid(fc_neg_data, fc_neg_titles, xlabel="Correlation", suptitle="Functional Connectome (Negative Edges)")

# # ----------- Demographics: Age & Brain Volume -----------

# demo_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_demographics_volume.csv"
# demo = pd.read_csv(demo_path)
# demo["ScanID"] = demo["ScanID"].astype(str)
# demo = demo[demo["ScanID"].str.endswith("_1")]

# males = demo[demo["Sex"] == "M"]
# females = demo[demo["Sex"] == "F"]

# # Age
# plot_histogram_grid(
#     [males["Age"], females["Age"]],
#     ["Males", "Females"],
#     xlabel="Age (years)",
#     suptitle="Age Distribution by Sex",
#     grid_shape=(1, 2)
# )

# # Brain Volume
# plot_histogram_grid(
#     [males["TotalBrainVolume"], females["TotalBrainVolume"]],
#     ["Males", "Females"],
#     xlabel="Total Brain Volume (mm³)",
#     suptitle="Total Brain Volume by Sex",
#     grid_shape=(1, 2)
# )

# # ----------- Fluid Intelligence -----------

# scores_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_scores.csv"
# scores = pd.read_csv(scores_path)

# plot_histogram_grid(
#     [scores["Fluid_Intelligence"]],
#     ["All Subjects"],
#     xlabel="Fluid Intelligence Score",
#     suptitle="Fluid Intelligence Histogram",
#     grid_shape=(1, 1)
# )


#  def plot_scatter(x, y, title, xlabel, ylabel, color='mediumorchid', show_line=True, group_labels=None):
#     """
#     Plot a scatter plot with optional regression line and grouping.
#     """
#     plt.figure(figsize=(7, 5))
    
#     if group_labels is not None:
#         groups = np.unique(group_labels)
#         for g in groups:
#             mask = group_labels == g
#             plt.scatter(x[mask], y[mask], label=str(g), alpha=0.6)
#     else:
#         plt.scatter(x, y, alpha=0.5, color=color)

#     if show_line:
#         slope, intercept, r, p, _ = linregress(x, y)
#         line_x = np.linspace(min(x), max(x), 100)
#         line_y = slope * line_x + intercept
#         plt.plot(line_x, line_y, color='black', linestyle='--', label=f"r = {r:.3f}, p = {p:.1e}")
#         plt.legend()

#     plt.title(title)
#     plt.xlabel(xlabel)
#     plt.ylabel(ylabel)
#     plt.grid(True, linestyle='--', alpha=0.4)
#     plt.tight_layout()
#     plt.show()

# # ---------- Scatter Plot 1: Structural vs Functional (0001_1) ----------

# struct_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_Counts.csv"
# func_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_RestEmpCorrFC.csv"

# struct = pd.read_csv(struct_path, header=None).values
# func = pd.read_csv(func_path, header=None).values

# tri_idx = np.triu_indices_from(struct, k=1)
# struct_vals = struct[tri_idx]
# func_vals = func[tri_idx]

# plot_scatter(
#     struct_vals, func_vals,
#     title="Structural vs Functional Connectome (0001_1)",
#     xlabel="Structural Weight (Streamline Count)",
#     ylabel="Functional Weight (Correlation)"
# )

# # ---------- Scatter Plot 2: Age vs Brain Volume by Sex ----------

# demo_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_demographics_volume.csv"
# demo = pd.read_csv(demo_path)
# demo = demo[demo["ScanID"].astype(str).str.endswith("_1")]

# plot_scatter(
#     demo["Age"], demo["TotalBrainVolume"],
#     title="Age vs Total Brain Volume",
#     xlabel="Age (years)", ylabel="Brain Volume (mm³)",
#     group_labels=demo["Sex"]
# )

# # ---------- Scatter Plot 3: Age vs Fluid Intelligence ----------

# scores_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_scores.csv"
# scores = pd.read_csv(scores_path)

# plot_scatter(
#     scores["Age"], scores["Fluid_Intelligence"],
#     title="Age vs Fluid Intelligence",
#     xlabel="Age (years)", ylabel="Fluid Intelligence",
#     group_labels=scores["Sex"]
# )

# # ---------- Scatter Plot 4: Processing Speed vs Fluid Intelligence ----------

# plot_scatter(
#     scores["Processing_Speed"], scores["Fluid_Intelligence"],
#     title="Processing Speed vs Fluid Intelligence",
#     xlabel="Processing Speed", ylabel="Fluid Intelligence",
#     group_labels=scores["Sex"]
# )


# def plot_boxplot(data_dict, title, ylabel, show=True):
#     """
#     Create a boxplot from a dictionary of label -> data array.
#     All boxes shown in the same figure window.
#     """
#     labels = list(data_dict.keys())
#     data = list(data_dict.values())

#     plt.figure(figsize=(10, 6))
#     plt.boxplot(data, labels=labels, showmeans=True, patch_artist=True)
#     plt.title(title)
#     plt.ylabel(ylabel)
#     plt.grid(True, linestyle='--', alpha=0.4)
#     if show:
#         plt.tight_layout()
#         plt.show()

# # ---------- Structural Connectome Node Strengths ----------

# def compute_node_strengths(matrix):
#     # Sum of streamline counts per node (excluding diagonal)
#     np.fill_diagonal(matrix, 0)
#     return matrix.sum(axis=1)

# struct_paths = {
#     "0001_1": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_Counts.csv",
#     "0001_2": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_2_Counts.csv",
#     "0002_1": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_1_Counts.csv",
#     "0002_2": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_2_Counts.csv"
# }

# node_strengths = {}
# for label, path in struct_paths.items():
#     mat = pd.read_csv(path, header=None).values
#     node_strengths[label] = compute_node_strengths(mat)

# plot_boxplot(node_strengths, title="Structural Connectome Node Strengths", ylabel="Summed Streamline Count")

# # ---------- Age Boxplot (Male vs Female) ----------

# demo_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_demographics_volume.csv"
# demo = pd.read_csv(demo_path)
# demo = demo[demo["ScanID"].astype(str).str.endswith("_1")]

# age_by_sex = {
#     "Male": demo[demo["Sex"] == "M"]["Age"],
#     "Female": demo[demo["Sex"] == "F"]["Age"]
# }

# plot_boxplot(age_by_sex, title="Age Distribution by Sex", ylabel="Age (years)")

# # ---------- Brain Volume Boxplot (GM/Subcortical/WM/Total) ----------

# volume_by_type = {
#     "Cortical GM": demo["Cortical_GM_volume"],
#     "Subcortical": demo["Subcortical_GM_volume"],
#     "WM": demo["White_Matter_volume"],
#     "Total": demo["TotalBrainVolume"]
# }

# plot_boxplot(volume_by_type, title="Brain Volume by Region (All Subjects)", ylabel="Volume (mm³)")

# # ---------- Brain Volume by Region AND Sex (Grouped Subplots) ----------

# fig, axs = plt.subplots(1, 2, figsize=(14, 6))

# for idx, (sex, ax) in enumerate([("M", axs[0]), ("F", axs[1])]):
#     subset = demo[demo["Sex"] == sex]
#     ax.boxplot([
#         subset["Cortical_GM_volume"],
#         subset["Subcortical_GM_volume"],
#         subset["White_Matter_volume"],
#         subset["TotalBrainVolume"]
#     ], labels=["Cortical GM", "Subcortical", "WM", "Total"], showmeans=True, patch_artist=True)
#     ax.set_title(f"{sex} Brain Volumes")
#     ax.set_ylabel("Volume (mm³)")
#     ax.grid(True, linestyle='--', alpha=0.4)

# plt.suptitle("Brain Volume by Region and Sex")
# plt.tight_layout(rect=[0, 0, 1, 0.95])
# plt.show()

# # ---------- Fluid Intelligence Boxplot (Male vs Female) ----------

# scores_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_scores.csv"
# scores = pd.read_csv(scores_path)

# fluid_by_sex = {
#     "Male": scores[scores["Sex"] == "M"]["Fluid_Intelligence"],
#     "Female": scores[scores["Sex"] == "F"]["Fluid_Intelligence"]
# }

# plot_boxplot(fluid_by_sex, title="Fluid Intelligence by Sex", ylabel="Score")

sns.set(style="whitegrid")

# ---------- Violin Plot Function ----------

def plot_violin(data_dict, title, ylabel, show=True):
    """
    Create violin plot from a dictionary of label -> 1D arrays.
    """
    df = pd.DataFrame({
        "Value": np.concatenate(list(data_dict.values())),
        "Group": np.concatenate([[label] * len(vals) for label, vals in data_dict.items()])
    })

    plt.figure(figsize=(10, 6))
    sns.violinplot(x="Group", y="Value", data=df, inner="box", palette="Set2")
    plt.title(title)
    plt.ylabel(ylabel)
    plt.xlabel("")
    if show:
        plt.tight_layout()
        plt.show()

# ---------- Load Structural Node Strengths ----------

def compute_node_strengths(matrix):
    np.fill_diagonal(matrix, 0)
    return matrix.sum(axis=1)

struct_paths = {
    "0001_1": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_1_Counts.csv",
    "0001_2": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0001_2_Counts.csv",
    "0002_1": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_1_Counts.csv",
    "0002_2": r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\0002_2_Counts.csv"
}

node_strengths = {
    label: compute_node_strengths(pd.read_csv(path, header=None).values)
    for label, path in struct_paths.items()
}

plot_violin(node_strengths, "Structural Connectome Node Strengths", "Summed Streamline Count")

# ---------- Load Demographics Data ----------

demo_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_demographics_volume.csv"
demo = pd.read_csv(demo_path)

# 🛠 DEBUG: Print column names to find the correct ID column
print("Demographics Columns:", demo.columns.tolist())

# 🛠 Replace 'CorrectColumnName' with actual ID column (e.g., 'SubjectID', 'ScanID', etc.)
demo = demo[demo["CorrectColumnName"].astype(str).str.endswith("_1")]

# ---------- Violin Plot: Age by Sex ----------

age_by_sex = {
    "Male": demo[demo["Sex"] == "M"]["Age"],
    "Female": demo[demo["Sex"] == "F"]["Age"]
}
plot_violin(age_by_sex, "Age Distribution by Sex", "Age (years)")

# ---------- Violin Plot: Brain Volume Types ----------

volume_by_type = {
    "Cortical GM": demo["Cortical_GM_volume"],
    "Subcortical": demo["Subcortical_GM_volume"],
    "White Matter": demo["White_Matter_volume"],
    "Total": demo["TotalBrainVolume"]
}
plot_violin(volume_by_type, "Brain Volume Types (All Subjects)", "Volume (mm³)")

# ---------- Load Scores + Fluid Intelligence by Sex ----------

scores_path = r"c:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 2\1000brains\1000BRAINS_scores.csv"
scores = pd.read_csv(scores_path)

fluid_by_sex = {
    "Male": scores[scores["Sex"] == "M"]["Fluid_Intelligence"],
    "Female": scores[scores["Sex"] == "F"]["Fluid_Intelligence"]
}
plot_violin(fluid_by_sex, "Fluid Intelligence by Sex", "Score")
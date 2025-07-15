# filename: plot_degrees.py

from helper import plot_boxplot

def plot_module_degrees_from_txt(file_path="degree_betweenModule.txt"):
    """
    Reads a text file where each line contains space-separated degree values for a module,
    and plots a boxplot using helper.plot_boxplot().
    """
    module_degrees = {}

    # Parse each line as a module
    with open(file_path, "r") as file:
        for idx, line in enumerate(file):
            values = list(map(int, line.strip().split()))
            module_label = f"Module {idx + 1}"
            module_degrees[module_label] = values

    # Plot using helper's boxplot
    plot_boxplot(
        module_degrees,
        title="Degree Between Modules",
        ylabel="Degree"
    )

# Execute if run directly
if __name__ == "__main__":
    plot_module_degrees_from_txt("degree_betweenModule.txt")

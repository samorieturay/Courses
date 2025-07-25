import os
import pandas as pd
import scipy.stats as stt
import helpers

# --- Make a Graphs directory for output ---
graphs_dir = os.path.join(os.getcwd(), "Graphs")
os.makedirs(graphs_dir, exist_ok=True)

# Path to your CSV files
base_path = r"C:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 4\bctWrapper\bctWrapper\src\1000BRAINS"
demographics = pd.read_csv(os.path.join(base_path, 'demographics.csv'))
scores = pd.read_csv(os.path.join(base_path, 'scores.csv'))

# Debug: print columns so you always know what's in the data
print("Demographics columns:", list(demographics.columns))
print("Scores columns:", list(scores.columns))

# Correct merge key for both files
MERGE_KEY = 'ID'

if MERGE_KEY not in demographics.columns or MERGE_KEY not in scores.columns:
    print(f"Column '{MERGE_KEY}' not found in both files! Please check and update MERGE_KEY.")
    exit(1)

data = pd.merge(scores, demographics, on=MERGE_KEY)

graph_measures = [
    'degree_avg',
    'degree_interHemisphere_avg',
    'degree_intraHemisphere_avg',
    'degree_withinModule_avg',
    'degree_betweenModule_avg'
]

cognitive_scores = [
    'Reasoning_raw',
    'Processing_Speed_raw',
    'Vocabulary_raw',
    'Naming_raw'
]

# --- 1. Correlations and plots ---

# (a) Between graph theory measures and age
for measure in graph_measures:
    r, p = stt.pearsonr(data[measure], data['Age'])
    outname = os.path.join(graphs_dir, f'corr_{measure}_age.png')
    helpers.drawCorrelationPlot(
        data[measure], data['Age'], r, p,
        measure, 'Age',
        f'Correlation between {measure} and Age',
        outname
    )

# (b) Between graph theory measures
for i, m1 in enumerate(graph_measures):
    for m2 in graph_measures[i+1:]:
        r, p = stt.pearsonr(data[m1], data[m2])
        outname = os.path.join(graphs_dir, f'corr_{m1}_{m2}.png')
        helpers.drawCorrelationPlot(
            data[m1], data[m2], r, p,
            m1, m2,
            f'Correlation between {m1} and {m2}',
            outname
        )

# (c) Between cognitive scores and graph theory measures
for cog in cognitive_scores:
    if cog not in data.columns:
        print(f"Warning: {cog} not in data columns, skipping...")
        continue
    for measure in graph_measures:
        r, p = stt.pearsonr(data[cog], data[measure])
        outname = os.path.join(graphs_dir, f'corr_{cog}_{measure}.png')
        helpers.drawCorrelationPlot(
            data[cog], data[measure], r, p,
            cog, measure,
            f'Correlation between {cog} and {measure}',
            outname
        )

# --- 2. Group differences and plots ---

# (a) Males vs Females (age and graph measures)
for val in ['Age'] + graph_measures:
    if val not in data.columns:
        print(f"Warning: {val} not in data columns, skipping box plot by sex...")
        continue
    males = data[data['Sex'] == 'Male'][val].dropna()
    females = data[data['Sex'] == 'Female'][val].dropna()
    outname = os.path.join(graphs_dir, f'box_{val}_sex.png')
    helpers.drawBoxPlot(
        [males, females],
        ['Male', 'Female'],
        f'{val} by Sex',
        outname,
        yLabel=val
    )

# (b) Young (22-40) vs Old (55-86), for graph measures and cognitive scores
young = data[(data['Age'] >= 22) & (data['Age'] <= 40)]
old = data[(data['Age'] >= 55) & (data['Age'] <= 86)]

for val in graph_measures + cognitive_scores:
    if val not in data.columns:
        print(f"Warning: {val} not in data columns, skipping box plot for age group...")
        continue
    ydat = young[val].dropna()
    odat = old[val].dropna()
    outname = os.path.join(graphs_dir, f'box_{val}_young_vs_old.png')
    helpers.drawBoxPlot(
        [ydat, odat],
        ['Young (22-40)', 'Old (55-86)'],
        f'{val}: Young vs Old',
        outname,
        yLabel=val
    )

print("All plots generated in the Graphs folder!")

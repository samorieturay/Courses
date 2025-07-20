import sys
sys.stdout.reconfigure(encoding='utf-8')

import pandas as pd
import scipy.stats as stt
import helpers
import os

# Use absolute path - notice bctWrapper\bctWrapper (no spaces)
base_path = r"C:\Users\smomo\OneDrive\Documents\Courses\CS455\Practice 4\bctWrapper\bctWrapper"

demographics_path = os.path.join(base_path, 'src', '1000BRAINS', 'demographics.csv')
scores_path = os.path.join(base_path, 'src', '1000BRAINS', 'scores.csv')

print(f"Looking for files at:")
print(f"  Demographics: {demographics_path}")
print(f"  Demographics exists: {os.path.exists(demographics_path)}")
print(f"  Scores: {scores_path}")
print(f"  Scores exists: {os.path.exists(scores_path)}")

if not os.path.exists(demographics_path) or not os.path.exists(scores_path):
    print("\n❌ Files not found! Please check:")
    print("1. The folder name (bctWrapper vs bctwrapper)")
    print("2. Whether the src/1000BRAINS folders exist")
    print("3. Whether the CSV files are actually there")
    exit(1)

demographics = pd.read_csv(demographics_path)
scores = pd.read_csv(scores_path)

# Merge on subject ID (update "Subject" if your column is named differently)
data = pd.merge(scores, demographics, on='Subject')

# --- Set your actual column names here! ---
graph_measures = ['Clustering', 'Efficiency', 'Degree']  # <<-- CHANGE THESE!
cognitive_scores = ['MMSE', 'MoCA']  # <<-- CHANGE THESE!

# --- 1. Correlations and plots ---

# (a) Between graph theory measures and age
for measure in graph_measures:
    r, p = stt.pearsonr(data[measure], data['Age'])
    outname = f'corr_{measure}_age.png'
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
        outname = f'corr_{m1}_{m2}.png'
        helpers.drawCorrelationPlot(
            data[m1], data[m2], r, p,
            m1, m2,
            f'Correlation between {m1} and {m2}',
            outname
        )

# (c) Between cognitive scores and graph theory measures
for cog in cognitive_scores:
    for measure in graph_measures:
        r, p = stt.pearsonr(data[cog], data[measure])
        outname = f'corr_{cog}_{measure}.png'
        helpers.drawCorrelationPlot(
            data[cog], data[measure], r, p,
            cog, measure,
            f'Correlation between {cog} and {measure}',
            outname
        )

# --- 2. Group differences and plots ---

# (a) Males vs Females (age and graph measures)
for val in ['Age'] + graph_measures:
    males = data[data['Sex'] == 'Male'][val].dropna()
    females = data[data['Sex'] == 'Female'][val].dropna()
    outname = f'box_{val}_sex.png'
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
    ydat = young[val].dropna()
    odat = old[val].dropna()
    outname = f'box_{val}_young_vs_old.png'
    helpers.drawBoxPlot(
        [ydat, odat],
        ['Young (22-40)', 'Old (55-86)'],
        f'{val}: Young vs Old',
        outname,
        yLabel=val
    )

print("All plots generated!")

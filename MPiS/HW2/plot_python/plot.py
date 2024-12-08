import matplotlib.pyplot as plt
import numpy as np
import math
import os

def create_plot(file_path, save_paths, divisions, titles):
    plots_dir = "plots"
    os.makedirs(plots_dir, exist_ok=True)

    with open(file_path, 'r') as file:
        data = [list(map(int, line.split())) for line in file]

    x_values = np.arange(1000, 100001, 1000)

    for p in range(len(divisions)):
        average_values = np.empty(100, dtype=float)

        plt.figure(figsize=(12, 8))
        for i, row in enumerate(data):
            y_values = row
            y_values_updated = np.empty_like(y_values)
            if divisions[p] == "none":
                y_values_updated = np.array(y_values)
            elif divisions[p] == "n":
                y_values_updated = np.array(y_values) / x_values[i]
            elif divisions[p] == "sqrtN":
                y_values_updated = np.array(y_values) / math.sqrt(x_values[i])
            elif divisions[p] == "nLnN":
                y_values_updated = np.array(y_values) / (x_values[i]*math.log(x_values[i]))
            elif divisions[p] == "nLnLnN":
                y_values_updated = np.array(y_values) / (x_values[i]*math.log(math.log(x_values[i])))
            elif divisions[p] == "n2":
                y_values_updated = np.array(y_values) / x_values[i]**2
            plt.plot([x_values[i]] * 50, y_values_updated, 's', color='gray', ms=2)
            average_values[i] = np.mean(y_values_updated)

        plt.plot(x_values, average_values, color='red', linewidth=3, label="Average Values")

        plt.title(titles[p])
        plt.xlabel("N Value")
        plt.ylabel("Result Value")

        plt.tight_layout()

        full_save_path = os.path.join(plots_dir, save_paths[p])
        plt.savefig(full_save_path)
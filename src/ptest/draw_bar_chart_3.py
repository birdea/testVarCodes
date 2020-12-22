from matplotlib import pyplot as plt
down = [1, 2, 2, 1, 2]
top = [2, 3, 4, 2, 1]
plt.bar(range(len(down)), down)
plt.bar(range(len(top)), top, bottom=down)
plt.legend(["TOP", "BOTTOM"])
plt.show()
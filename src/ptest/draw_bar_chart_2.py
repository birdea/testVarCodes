from matplotlib import pyplot as plt
import numpy as np
topics = ['one', 'two', 'three', 'four', 'five']
As = [6, 3, 4, 3, 50]
Bs = [8, 12, 8, 9, 10]
Cs = [13, 12, 15, 13, 14]
Ds = [2, 3, 3, 2, 1]
Fs = [1, 0, 0, 3, 0]
c_bottom = np.add(As, Bs)
d_bottom = np.add(c_bottom, Cs)
f_bottom = np.add(d_bottom, Ds)
x = range(len(topics))
plt.bar(x, As)
plt.bar(x, Bs, bottom=As)
plt.bar(x, Cs, bottom=c_bottom)
plt.bar(x, Ds, bottom=d_bottom)
plt.bar(x, Fs, bottom=f_bottom)
ax = plt.subplot()
ax.set_xticks(x)
ax.set_xticklabels(topics)
plt.title('TITLE')
plt.xlabel('X LABEL')
plt.ylabel('Y LABEL')
plt.show()
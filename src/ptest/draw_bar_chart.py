import matplotlib.pyplot as plt
import numpy as np
from matplotlib import font_manager, rc
from matplotlib import style

#font_name = font_manager.FontProperties(fname="c:/Windows/Fonts/malgun.ttf").get_name()
# rc('font', family="utf-8")
# style.use('ggplot')
#
# industry = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j']
# fluctuations = [1.83, 1.30, 1.30, 1.26, 1.06, 0.93, 0.77, 0.68, 0.65, 0.61]
#
# fig = plt.figure(figsize=(12, 8))
# ax = fig.add_subplot(111)
#
# ypos = np.arange(10)
# rects = plt.barh(ypos, fluctuations, align='center', height=0.5)
# plt.yticks(ypos, industry)
#
# for i, rect in enumerate(rects):
#     ax.text(0.95 * rect.get_width(), rect.get_y() + rect.get_height() / 2.0, str(fluctuations[i]) + '%', ha='right', va='center')
#
# plt.xlabel('등락률')
# plt.show()
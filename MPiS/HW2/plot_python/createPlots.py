from plot import create_plot

save_path_B = ["plotB.png", "plotBbyN.png", "plotBbySqrtN.png"]
divisions_B = ["none", "n", "sqrtN"]
titles_B = ["First Collision", "First Collision by N", "First Collision by sqrt(N)"]

create_plot("firstCollision.txt", save_path_B, divisions_B, titles_B)


save_path_U = ["plotU.png", "plotUbyN.png"]
divisions_U = ["none", "n"]
titles_U = ["Empty Bins After N Balls", "Empty Bins After N Balls by N"]

create_plot("emptyBinsAfterNBalls.txt", save_path_U, divisions_U, titles_U)


save_path_C = ["plotC.png", "plotCbyN.png", "plotCbyNlnN.png", "plotCbyN2.png"]
divisions_C = ["none", "n", "nLnN", "n2"]
titles_C = ["Coupon Collector", "Coupon Collector by N", "Coupon Collector by N*ln(N)", "Coupon Collector by N^2"]

create_plot("couponCollector.txt", save_path_C, divisions_C, titles_C)


save_path_D = ["plotD.png", "plotDbyN.png", "plotDbyNlnN.png", "plotDbyN2.png"]
divisions_D = ["none", "n", "nLnN", "n2"]
titles_D = ["Coupon Collector's Brother", "Coupon Collector's Brother by N", "Coupon Collector's Brother by N*ln(N)", "Coupon Collector's Brother by N^2"]

create_plot("couponCollectorBrother.txt", save_path_D, divisions_D, titles_D)


save_path_DC = ["plotDC.png", "plotDCbyN.png", "plotDCbyNlnN.png", "plotDCbyNlnLnN.png"]
divisions_DC = ["none", "n", "nLnN", "nLnLnN"]
titles_DC = ["D(n) - C(n)", "(D(n) - C(n)) by N", "(D(n) - C(n)) by N*ln(N)", "(D(n) - C(n)) by N*ln(ln(N))"]

create_plot("numberOfBallsSinceCn.txt", save_path_DC, divisions_DC, titles_DC)


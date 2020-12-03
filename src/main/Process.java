
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Process {

    private long calculation = 0, affected = 0;

    public int[][][] oilPaint(int[][][] p) {

        int temp[][][] = new int[p.length][p[0].length][3];

        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                int r1 = 0, r2 = 0, g1 = 0, g2 = 0, b1 = 0, b2 = 0;

                if (y + 1 < p.length && x + 1 < p[y].length) {
                    r1 = p[y + 1][x + 1][0];
                    g1 = p[y + 1][x + 1][1];
                    b1 = p[y + 1][x + 1][2];
                    calculation += 3;
                } else {
                    r1 = p[y][x][0];
                    g1 = p[y][x][1];
                    b1 = p[y][x][2];
                    affected++;
                }
                if (y + 2 < p.length && x + 2 < p[y].length) {
                    r2 = p[y + 2][x + 2][0];
                    g2 = p[y + 2][x + 2][1];
                    b2 = p[y + 2][x + 2][2];
                    calculation += 3;
                } else {
                    r2 = p[y][x][0];
                    g2 = p[y][x][1];
                    b2 = p[y][x][2];
                    calculation += 3;
                }

                Color c1 = new Color(r1, g1, b1);
                Color c2 = new Color(r2, g2, b2);

                affected++;
                temp[y][x][0] = (c1.getRGB() + c2.getRGB()) / 2;
            }
        }

        return temp;
    }

    public int[][][] blur(int p[][][]) {
        int res[][][] = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {

                try {

                    int c = 0;

                    int red = 0, green = 0, blue = 0;

                    if (y - 1 >= 0 && x - 1 >= 0) {
                        red += p[y - 1][x - 1][0];
                        c++;
                        calculation++;
                    }
                    if (y - 1 >= 0) {
                        red += p[y - 1][x][0];
                        c++;
                        calculation++;
                    }
                    if (y - 1 >= 0 && x + 1 < p[y].length) {
                        red += p[y - 1][x + 1][0];
                        c++;
                        calculation++;
                    }

                    if (x - 1 >= 0) {
                        red += p[y][x - 1][0];
                        c++;
                        calculation++;
                    }
                    if (x == x) {
                        red += p[y][x][0];
                        c++;
                        calculation++;
                    }
                    if (x + 1 < p[y].length) {
                        red += p[y][x + 1][0];
                        c++;
                        calculation++;
                    }

                    if (y + 1 < p.length && x - 1 >= 0) {
                        red += p[y + 1][x - 1][0];
                        c++;
                        calculation++;
                    }
                    if (y + 1 < p.length) {
                        red += p[y + 1][x][0];
                        c++;
                        calculation++;
                    }
                    if (y + 1 < p.length && x + 1 < p[y].length) {
                        red += p[y + 1][x + 1][0];
                        c++;
                        calculation++;
                    }

                    if (y - 1 >= 0 && x - 1 >= 0) {
                        green += p[y - 1][x - 1][1];
                        calculation++;
                    }
                    if (y - 1 >= 0) {
                        green += p[y - 1][x][1];
                        calculation++;
                    }
                    if (y - 1 >= 0 && x + 1 < p[y].length) {
                        green += p[y - 1][x + 1][1];
                        calculation++;
                    }

                    if (x - 1 >= 0) {
                        green += p[y][x - 1][1];
                        calculation++;
                    }
                    if (x == x) {
                        green += p[y][x][1];
                        calculation++;
                    }
                    if (x + 1 < p[y].length) {
                        green += p[y][x + 1][1];
                        calculation++;
                    }

                    if (y + 1 < p.length && x - 1 >= 0) {
                        green += p[y + 1][x - 1][1];
                        calculation++;
                    }
                    if (y + 1 < p.length) {
                        green += p[y + 1][x][1];
                        calculation++;
                    }
                    if (y + 1 < p.length && x + 1 < p[y].length) {
                        green += p[y + 1][x + 1][1];
                        calculation++;
                    }

                    if (y - 1 >= 0 && x - 1 >= 0) {
                        blue += p[y - 1][x - 1][2];
                        calculation++;
                    }
                    if (y - 1 >= 0) {
                        blue += p[y - 1][x][2];
                        calculation++;
                    }
                    if (y - 1 >= 0 && x + 1 < p[y].length) {
                        blue += p[y - 1][x + 1][2];
                        calculation++;
                    }

                    if (x - 1 >= 0) {
                        blue += p[y][x - 1][2];
                        calculation++;
                    }
                    if (x == x) {
                        blue += p[y][x][2];
                        calculation++;
                    }
                    if (x + 1 < p[y].length) {
                        blue += p[y][x + 1][2];
                        calculation++;
                    }

                    if (y + 1 < p.length && x - 1 >= 0) {
                        blue += p[y + 1][x - 1][2];
                        calculation++;
                    }
                    if (y + 1 < p.length) {
                        blue += p[y + 1][x][2];
                        calculation++;
                    }
                    if (y + 1 < p.length && x + 1 < p[y].length) {
                        blue += p[y + 1][x + 1][2];
                        calculation++;
                    }

                    red /= c;
                    green /= c;
                    blue /= c;
                    calculation += 3;

                    res[y][x][0] = red;
                    res[y][x][1] = green;
                    res[y][x][2] = blue;
                    affected++;

                } catch (Exception r) {
                    JOptionPane.showMessageDialog(null, r);
                }
            }
        }
        return res;
    }

    public int[][][] filter(int[][][] p, int r1, int r2, int g1, int g2, int b1, int b2) {
        int[][][] res = new int[p.length][p[0].length][3];
        try {

            for (int y = 0; y < p.length; y++) {
                for (int x = 0; x < p[y].length; x++) {
                    if (r1 == -1 && g1 == -1 && b1 == -1) {
                        return p;
                    }
                    if (r2 != -1 && g2 != -1 && b2 != -1) {
                        if (r1 != -1 && g1 != -1 && b1 != -1) {
                            res = filterRed(p, r1, r2);
                            res = filterGreen(res, g1, g2);
                            res = filterBlue(res, b1, b2);
                            return res;
                        }
                    } else if (r2 != -1 && g2 != -1 && b2 == -1) {
                        if (r1 != -1 && g1 != -1 && b1 == -1) {
                            res = filterRed(p, r1, r2);
                            res = filterGreen(res, g1, g2);
                            return res;
                        } else if (r1 != -1 && g1 != -1 && b1 != -1) {
                            res = filterRed(p, r1, r2);
                            res = filterGreen(res, g1, g2);
                            res = filterBlue(res, b1, b1);
                            return res;
                        } else {
                            return null;
                        }
                    } else if (r2 != -1 && b2 != -1 && g2 == -1) {
                        if (r1 != -1 && b1 != -1 && g1 == -1) {
                            res = filterRed(p, r1, r2);
                            res = filterBlue(res, b1, b2);
                            return res;
                        } else if (r1 != -1 && b1 != -1 && g1 != -1) {
                            res = filterRed(p, r1, r2);
                            res = filterBlue(res, b1, b2);
                            res = filterGreen(res, g1, g1);
                            return res;
                        } else {
                            return null;
                        }
                    } else if (b2 != -1 && g2 != -1 && r2 == -1) {
                        if (b1 != -1 && g1 != -1 && r1 == -1) {
                            res = filterGreen(p, g1, g2);
                            res = filterBlue(res, b1, b2);
                            return res;
                        } else if (b1 != -1 && g1 != -1 && r1 != -1) {
                            res = filterGreen(p, g1, g2);
                            res = filterBlue(res, b1, b2);
                            res = filterRed(res, r1, r1);
                            return res;
                        } else {
                            return null;
                        }
                    } else if (r2 != -1 && g2 == -1 && b2 == -1) {
                        if (r1 != -1 && g1 == -1 && b1 == -1) {
                            res = filterRed(p, r1, r2);
                            return res;
                        } else if (r1 != -1 && g1 != -1 && b1 == -1) {
                            res = filterRed(p, r1, r2);
                            res = filterGreen(res, g1, g1);
                            return res;
                        } else if (r1 != -1 && g1 == -1 && b1 != -1) {
                            res = filterRed(p, r1, r2);
                            res = filterBlue(res, b1, b1);
                            return res;
                        } else if (r1 != -1 && g1 != -1 && b1 != -1) {
                            res = filterRed(p, r1, r2);
                            res = filterGreen(res, g1, g1);
                            res = filterBlue(res, b1, b1);
                            return res;
                        } else {
                            return null;
                        }
                    } else if (g2 != -1 && r2 == -1 && b2 == -1) {
                        if (g1 != -1 && b1 == -1 && r1 == -1) {
                            res = filterGreen(p, g1, g2);
                            return res;
                        } else if (g1 != -1 && b1 != -1 && r1 == -1) {
                            res = filterGreen(p, g1, g2);
                            res = filterBlue(res, b1, b1);
                            return res;
                        } else if (g1 != -1 && b1 == -1 && r1 != -1) {
                            res = filterGreen(p, g1, g2);
                            res = filterRed(res, r1, r1);
                            return res;
                        } else if (g1 != -1 && b1 != -1 && r1 != -1) {
                            res = filterGreen(p, g1, g2);
                            res = filterBlue(res, b1, b1);
                            res = filterRed(res, r1, r1);
                            return res;
                        } else {
                            return null;
                        }
                    } else if (b2 != -1 && r2 == -1 && g2 == -1) {
                        if (b1 != -1 && r1 == -1 && g1 == -1) {
                            res = filterBlue(p, b1, b2);
                            return res;
                        } else if (b1 != -1 && r1 != -1 && g1 == -1) {
                            res = filterBlue(p, b1, b2);
                            res = filterRed(res, r1, r1);
                            return res;
                        } else if (b1 != -1 && r1 == -1 && g1 != -1) {
                            res = filterBlue(p, b1, b2);
                            res = filterGreen(res, r1, r1);
                            return res;
                        } else if (b1 != -1 && r1 != -1 && g1 != -1) {
                            res = filterBlue(p, b1, b2);
                            res = filterRed(res, r1, r1);
                            res = filterGreen(res, g1, g1);
                            return res;
                        } else {
                            return null;
                        }
                    } else if (r2 == -1 && g2 == -1 && b2 == -1) {

                        if (r1 == -1 && g1 == -1 && b1 == -1) {
                            return null;
                        }
                        res = this.filterSingle(p, r1, g1, b1);
                        return res;

                    }

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return res;
    }

    public int[][][] add(int[][][] p, BufferedImage i, double range) {
        int[][][] res = new int[p.length][p[0].length][3];
        Image ii = i.getScaledInstance(p[0].length, p.length, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(p[0].length, p.length, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.createGraphics();
        g.drawImage(ii, 0, 0, null);

        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                double red = (p[y][x][0] * (1.0 - range)) + (new Color(newImg.getRGB(x, y)).getRed() * range);
                double green = (p[y][x][0] * (1.0 - range)) + (new Color(newImg.getRGB(x, y)).getGreen() * range);
                double blue = (p[y][x][0] * (1.0 - range)) + (new Color(newImg.getRGB(x, y)).getBlue() * range);
                res[y][x][0] = (int) (red > 255 ? 255 : red);
                res[y][x][1] = (int) (green > 255 ? 255 : green);
                res[y][x][2] = (int) (blue > 255 ? 255 : blue);
                this.affected++;
                this.calculation += 3;
            }
        }
        return res;
    }

    public int[][][] addRed(int[][][] p, BufferedImage i, double range) {
        int[][][] res = new int[p.length][p[0].length][3];
        Image ii = i.getScaledInstance(p[0].length, p.length, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(p[0].length, p.length, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.createGraphics();
        g.drawImage(ii, 0, 0, null);
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                double red = (p[y][x][0] * (1.0 - range)) + (new Color(newImg.getRGB(x, y)).getRed() * range);

                res[y][x][0] = (int) (red > 255 ? 255 : red);
                res[y][x][1] = p[y][x][1];
                res[y][x][2] = p[y][x][2];
                this.affected++;
                this.calculation++;
            }
        }
        return res;
    }

    public int[][][] addGreen(int[][][] p, BufferedImage i, double range) {
        int[][][] res = new int[p.length][p[0].length][3];
        Image ii = i.getScaledInstance(p[0].length, p.length, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(p[0].length, p.length, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.createGraphics();
        g.drawImage(ii, 0, 0, null);
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {

                double green = (p[y][x][0] * (1.0 - range)) + (new Color(newImg.getRGB(x, y)).getGreen() * range);

                res[y][x][0] = p[y][x][0];
                res[y][x][1] = (int) (green > 255 ? 255 : green);
                res[y][x][2] = p[y][x][2];
                this.affected++;
                this.calculation++;
            }
        }
        return res;
    }

    public int[][][] addBlue(int[][][] p, BufferedImage i, double range) {
        int[][][] res = new int[p.length][p[0].length][3];
        Image ii = i.getScaledInstance(p[0].length, p.length, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(p[0].length, p.length, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.createGraphics();
        g.drawImage(ii, 0, 0, null);
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {

                double blue = (p[y][x][0] * (1.0 - range)) + (new Color(newImg.getRGB(x, y)).getBlue() * range);
                res[y][x][0] = p[y][x][0];
                res[y][x][1] = p[y][x][1];
                res[y][x][2] = (int) (blue > 255 ? 255 : blue);
                this.affected++;
                this.calculation++;
            }
        }
        return res;
    }

    private int[][][] filterRed(int[][][] p, int r1, int r2) {
        int[][][] res = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                for (int r = r1; r <= r2; r++) {
                    if (p[y][x][0] == r) {
                        res[y][x][0] = r;
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        affected++;
                    }
                    calculation++;
                }
            }
        }
        return res;
    }
    
    private int[][][] filterGreen(int[][][] p, int g1, int g2) {
        int[][][] res = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                for (int g = g1; g <= g2; g++) {
                    if (p[y][x][1] == g) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = g;
                        res[y][x][2] = p[y][x][2];
                        affected++;
                    }
                    calculation++;
                }
            }
        }
        return res;
    }

    private int[][][] filterBlue(int[][][] p, int b1, int b2) {
        int[][][] res = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                for (int b = b1; b <= b2; b++) {
                    if (p[y][x][2] == b) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = b;
                        affected++;
                    }
                    calculation++;
                }
            }
        }
        return res;
    }

    private int[][][] filterSingle(int[][][] p, int r1, int g1, int b1) {
        int[][][] res = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                if (r1 != -1 && g1 != -1 && b1 != -1) {
                    if (p[y][x][0] == r1 && p[y][x][1] == g1 && p[y][x][2] == b1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else if (r1 != -1 && g1 != -1 && b1 == -1) {
                    if (p[y][x][0] == r1 && p[y][x][1] == g1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else if (r1 != -1 && g1 == -1 && b1 != -1) {
                    if (p[y][x][0] == r1 && p[y][x][2] == b1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else if (r1 == -1 && g1 != -1 && b1 != -1) {
                    if (p[y][x][1] == g1 && p[y][x][2] == b1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else if (r1 != -1 && g1 == -1 && b1 == -1) {
                    if (p[y][x][0] == r1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else if (r1 == -1 && g1 != -1 && b1 == -1) {
                    if (p[y][x][1] == g1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else if (r1 == -1 && g1 == -1 && b1 != -1) {
                    if (p[y][x][2] == b1) {
                        res[y][x][0] = p[y][x][0];
                        res[y][x][1] = p[y][x][1];
                        res[y][x][2] = p[y][x][2];
                        this.affected++;
                        this.calculation += 3;
                    }
                } else {
                    return null;
                }
            }
        }
        return res;
    }

    public int[][][] getRed(int[][][] p) {
        int[][][] red = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                red[y][x][0] = p[y][x][0];
            }
        }
        return red;
    }

    public int[][][] getGreen(int[][][] p) {
        int[][][] red = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                red[y][x][1] = p[y][x][1];
            }
        }
        return red;
    }

    public int[][][] getBlue(int[][][] p) {
        int[][][] red = new int[p.length][p[0].length][3];
        for (int y = 0; y < p.length; y++) {
            for (int x = 0; x < p[y].length; x++) {
                red[y][x][2] = p[y][x][2];
            }
        }
        return red;
    }

    public void printCalculation(JTextArea a) {
        a.append("Total Calculations: " + this.calculation + "\n");
        this.calculation = 0;
    }

    public void printPixelsAfected(JTextArea a) {
        a.append("Total Pixels affected: " + this.affected + "\n");
        this.affected = 0;
    }

}

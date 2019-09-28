package Lab1.Minimum.Interface;

import Lab1.Minimum.WrongMinimum;

public interface IntFunction {
  abstract double func(int x);

  abstract double findMin(int from, int to, int step) throws WrongMinimum;
}

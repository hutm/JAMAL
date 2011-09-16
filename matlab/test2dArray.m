function [sumOut] = test2dArray(inMatrix)

in = javaCellArgs2Matlab(inMatrix);
sumOut = sum(in(:));
display(sumOut);

end
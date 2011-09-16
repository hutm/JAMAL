function [matlabArray] = javaCellArgs2Matlab(inCells)
% [matlabArray]=javaCellArgs2Matlab(arg1)
%   Take array of Matlab cells that represent 2D java array passed from a java program 
% and transforms it into a Matlab array
% 2010-09-27 Harry Rostovtsev - initial version
% 2010-10-10 Maksim Khadkevich - minor changes


yDim = size(inCells, 2);
xDim = size(inCells{1}, 2);

tmpCellArr = [inCells{:}];
tmpReshapedArr = reshape(tmpCellArr, xDim, yDim);
matlabArray = transpose(tmpReshapedArr);


end

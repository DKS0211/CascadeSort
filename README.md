# CascadeSort
Cascade Sort - a new adaptive sorting algorithm for nearly-sorted element

## How it works
1. Find all adjacent inversions (where arr[i] > arr[i+1]
2. Expand windows around each inversion until stable
3. Merge overlapping windows
4. Sort only the messy zones

## Usage
compile and run :

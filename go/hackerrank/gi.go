package hackerrank

import "sort"

/*
 * Complete the 'maxDifference' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY px as parameter.
 */
func maxDifference(px []int32) int32 {
	var md int32 = -1

	for i := len(px) - 1; i >= 0; i-- {
		for j := i - 1; j >= 0; j-- {
			d := px[i] - px[j]
			if d > md {
				md = d
			}
		}
	}

	return md
}

/*
 * Complete the 'RemainderSorting' function below (and other code for sorting if needed).
 *
 * The function is expected to return a STRING_ARRAY.
 * The function accepts STRING_ARRAY strArr as parameter.
 */
func RemainderSorting(strArr []string) []string {
	sort.Slice(strArr, func(i, j int) bool {

		lsi := len(strArr[i]) % 3
		lsj := len(strArr[j]) % 3

		if lsi == lsj {
			return strArr[i] < strArr[j]
		}

		return lsi < lsj
	})

	return strArr
}

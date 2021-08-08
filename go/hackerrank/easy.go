package hackerrank

import (
	"fmt"
	"math"
	"sort"
	"strconv"
	"strings"
)

// https://www.hackerrank.com/challenges/flatland-space-stations/submissions/code/227517765
// Complete the flatlandSpaceStations function below.
func flatlandSpaceStations(n int32, c []int32) int32 {
	sort.Slice(c, func(i, j int) bool {
		return c[i] < c[j]
	})

	m := c[0]
	for i := 1; i < len(c); i++ {
		d := c[i] - c[i-1]
		d = d / 2
		if d > m {
			m = d
		}
	}

	lc := (n - 1) - c[len(c)-1]
	if lc > m {
		m = lc
	}

	return m
}

/* https://www.hackerrank.com/challenges/lisa-workbook/submissions/code/227515520
 * Complete the 'workbook' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. INTEGER k
 *  3. INTEGER_ARRAY arr
 */
func workbook(n int32, mpp int32, npc []int32) int32 {
	// mpp Max Problems per Page
	// npc Number of Problems per Chapter

	var sp int32 = 0 // Special Problems
	var pa int32 = 1 // Page
	var pr int32 = 0 // Problem

	var pip int32 = 0 // Problems in Page
	var pic int32 = 0 // Problems in Chapter

	for _, pic = range npc {
		pr = 1
		if pip != 0 {
			pip = 0
			pa++
		}

		for pr <= pic {
			pip++

			if pr == pa {
				sp++
			}

			if pip == mpp {
				pa++
				pip = 0
			}

			pr++
		}
	}

	return sp
}

/* https://www.hackerrank.com/challenges/service-lane/submissions/code/227497447
 * Complete the 'serviceLane' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. 2D_INTEGER_ARRAY cases
 */
func serviceLane(width []int32, cases [][]int32) (result []int32) {

	for _, c := range cases {
		slice := width[c[0] : c[1]+1]
		result = append(result, min(slice))
	}

	return
}
func min(ss []int32) int32 {
	m := ss[0]
	for _, s := range ss[1:] {
		if s < m {
			m = s
		}
	}
	return m
}

/* https://www.hackerrank.com/challenges/ctci-ransom-note/submissions/code/227478388
 * Complete the 'checkMagazine' function below.
 *
 * The function accepts following parameters:
 *  1. STRING_ARRAY magazine
 *  2. STRING_ARRAY note
 */
func checkMagazine(magazine []string, note []string) {
	noteWords := make(map[string]int)
	for _, word := range note {
		noteWords[word] = noteWords[word] + 1
	}

	missingWords := len(note)
	for _, word := range magazine {
		if noteWords[word] != 0 {
			noteWords[word] = noteWords[word] - 1
			missingWords--
		}
		if missingWords == 0 {
			fmt.Println("Yes")
			return
		}
	}
	fmt.Println("No")
}

/* https://www.hackerrank.com/challenges/2d-array/submissions/code/227475303
 * Complete the 'hourglassSum' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts 2D_INTEGER_ARRAY arr as parameter.
 */
func hourglassSum(arr [][]int32) int32 {
	max := float64(math.MinInt32)
	for x0 := 0; x0 <= 3; x0++ {
		x1, x2 := x0+1, x0+2
		for y0 := 0; y0 <= 3; y0++ {
			y1, y2 := y0+1, y0+2

			sum := arr[x0][y0] + arr[x0][y1] + arr[x0][y2] +
				arr[x1][y1] +
				arr[x2][y0] + arr[x2][y1] + arr[x2][y2]

			max = math.Max(max, float64(sum))
		}
	}
	return int32(max)
}

/* https://www.hackerrank.com/challenges/breaking-best-and-worst-records/submissions/code/226068466
 * Complete the 'breakingRecords' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY scores as parameter.
 */
func breakingRecords(scores []int32) []int32 {
	var cH, cL int32
	highest, lowest := scores[0], scores[0]

	for _, s := range scores {
		switch {
		case s < lowest:
			lowest = s
			cL++
		case s > highest:
			highest = s
			cH++
		}

	}

	return []int32{cH, cL}
}

/* https://www.hackerrank.com/challenges/cats-and-a-mouse/submissions/code/226064568
 * Complete the catAndMouse function below.
 */
func catAndMouse(x int32, y int32, z int32) string {
	catA := math.Abs(float64(z - x))
	catB := math.Abs(float64(z - y))

	switch {
	case catA < catB:
		return "Cat A"
	case catA > catB:
		return "Cat B"
	default:
		return "Mouse C"
	}
}

/* https://www.hackerrank.com/challenges/apple-and-orange/submissions/code/226063206
 * Complete the 'countApplesAndOranges' function below.
 *
 * The function accepts following parameters:
 *  1. INTEGER s
 *  2. INTEGER t
 *  3. INTEGER a
 *  4. INTEGER b
 *  5. INTEGER_ARRAY apples
 *  6. INTEGER_ARRAY oranges
 */
func countApplesAndOranges(s int32, t int32, a int32, b int32, apples []int32, oranges []int32) {
	house := [...]int32{s, t}
	fmt.Println(countFruits(house, a, apples))
	fmt.Println(countFruits(house, b, oranges))
}
func countFruits(house [2]int32, tree int32, fruits []int32) int {
	onHouse := 0
	for _, f := range fruits {
		l := f + tree
		if house[0] <= l && l <= house[1] {
			onHouse++
		}
	}
	return onHouse
}

/* https://www.hackerrank.com/challenges/grading/submissions/code/226061302
 * Complete the 'gradingStudents' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY grades as parameter.
 */
func gradingStudents(grades []int32) []int32 {
	for i, g := range grades {
		if g < 38 {
			continue
		}

		round := g - g%5 + 5
		if round-g < 3 {
			grades[i] = round
		}

	}
	return grades
}

/* https://www.hackerrank.com/challenges/time-conversion/submissions/code/225727140
 * Complete the 'timeConversion' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING s as parameter.
 */
func timeConversion(s string) string {

	m, s := s[len(s)-2:len(s)], s[:len(s)-2]

	ss := strings.Split(s, ":")
	h, _ := strconv.Atoi(ss[0])

	if m == "AM" && h == 12 {
		ss[0] = "00"
	}

	if m == "PM" && h != 12 {
		h += 12
		ss[0] = strconv.Itoa(h)
	}

	s = strings.Join(ss, ":")

	return s
}

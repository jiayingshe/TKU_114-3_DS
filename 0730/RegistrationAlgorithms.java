import java.util.ArrayList;
import java.util.List;

public class RegistrationAlgorithms {

    public static void mergeSortByIdAsc(List<Registration> list, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByIdAsc(list, left, mid);
        mergeSortByIdAsc(list, mid + 1, right);
        mergeByIdAsc(list, left, mid, right);
    }

    private static void mergeByIdAsc(List<Registration> list, int left, int mid, int right) {
        List<Registration> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (list.get(i).getId() <= list.get(j).getId()) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }
        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    public static Registration binarySearchById(List<Registration> list, int targetId) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).getId() == targetId) {
                return list.get(mid);
            } else if (list.get(mid).getId() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static Registration sequentialSearchByName(List<Registration> list, String name) {
        for (Registration r : list) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }
}
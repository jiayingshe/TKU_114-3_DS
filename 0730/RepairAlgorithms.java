import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    public static void mergeSortByPriorityDesc(List<RepairTask> tasks, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByPriorityDesc(tasks, left, mid);
        mergeSortByPriorityDesc(tasks, mid + 1, right);
        mergeByPriorityDesc(tasks, left, mid, right);
    }

    private static void mergeByPriorityDesc(List<RepairTask> tasks, int left, int mid, int right) {
        List<RepairTask> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (tasks.get(i).getPriority() >= tasks.get(j).getPriority()) {
                temp.add(tasks.get(i++));
            } else {
                temp.add(tasks.get(j++));
            }
        }
        while (i <= mid) temp.add(tasks.get(i++));
        while (j <= right) temp.add(tasks.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            tasks.set(left + k, temp.get(k));
        }
    }

    public static List<RepairTask> sequentialSearchByDevice(List<RepairTask> tasks, String deviceName) {
        List<RepairTask> result = new ArrayList<>();
        for (RepairTask t : tasks) {
            if (t.getDeviceName().equalsIgnoreCase(deviceName)) {
                result.add(t);
            }
        }
        return result;
    }
}
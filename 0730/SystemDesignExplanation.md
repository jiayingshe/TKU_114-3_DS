# 0730 課後作業五：資料結構與演算法選擇說明

本文件針對 0730 課後作業系統設計，挑選 6 個核心功能進行資料結構與演算法之選用分析。

---

### 1. 主資料保存與動態擴充
* **對應檔案**：`LibraryManagementSystem.java`
* **方法名稱**：`addBook(Book book)`
* **採用技術**：`ArrayList`
* **選擇原因**：需要連續記憶體結構維護整體主資料，支援索引存取與動態長度擴充。
* **未採用其他方法原因**：原生陣列（Array）固定長度無法應付動態新增；`LinkedList` 隨機存取效能較差。

---

### 2. 維修工作與候補佇列排隊
* **對應檔案**：`RepairSchedulingSystem.java`
* **方法名稱**：`completeNextTask()`
* **採用技術**：`Queue (LinkedList)`
* **選擇原因**：嚴格遵守先進先出（FIFO）邏輯，符合任務依序排隊與遞補之實務需求。
* **未採用其他方法原因**：`Stack` 會造成後進先出（LIFO），破壞排隊公平性；`ArrayList` 在前端刪除元素需要 $O(N)$ 時間進行位移。

---

### 3. 操作復原與取消記錄管理
* **對應檔案**：`RepairSchedulingSystem.java` / `EventRegistrationSystem.java`
* **方法名稱**：`undoCompletedTasks(int count)` / `undoCancellations(int count)`
* **採用技術**：`Stack`
* **選擇原因**：符合後進先出（LIFO）特性，能以最快速度恢復最近一次被處置的項目。
* **未採用其他方法原因**：`Queue` 無法直接提取最後加入的資料。

---

### 4. 特定鍵值之精確快速查詢
* **對應檔案**：`BookAlgorithms.java` / `RegistrationAlgorithms.java`
* **方法名稱**：`binarySearchById(...)`
* **採用技術**：`二分查找 (Binary Search)`
* **選擇原因**：對於已排序之主資料，查詢時間複雜度僅需 $O(\log N)$，在大數據量下效率極高。
* **未採用其他方法原因**：`順序查找 (Sequential Search)` 時間複雜度高達 $O(N)$，效能顯著落後。

---

### 5. 非唯一/未排序欄位之文字查詢
* **對應檔案**：`BookAlgorithms.java` / `RegistrationAlgorithms.java`
* **方法名稱**：`sequentialSearchByTitle(...)` / `sequentialSearchByName(...)`
* **採用技術**：`順序查找 (Sequential Search)`
* **選擇原因**：字串與非排序鍵值欄位無法滿足二分查找前提，順序查找實作簡單且不需預先排序。
* **未採用其他方法原因**：若要進行二分查找，必須額外建立索引陣列並排序，維護成本較高。

---

### 6. 多欄位與安定性排序需求
* **對應檔案**：`BookAlgorithms.java` / `RepairAlgorithms.java`
* **方法名稱**：`mergeSortByIdAsc(...)` / `mergeSortByPriorityDesc(...)`
* **採用技術**：`歸併排序 (Merge Sort)`
* **選擇原因**：提供穩定的 $O(N \log N)$ 時間複雜度，且屬於安定排序（Stable Sort），可保證相同鍵值資料之原始相對順序不被破壞。
* **未採用其他方法原因**：`選擇排序` 與 `插入排序` 時間複雜度為 $O(N^2)$；`快速排序 (Quick Sort)` 為不安定排序。
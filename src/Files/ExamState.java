package Files;

public enum ExamState {
    MANAGING_EXAMS("src\\Files\\files\\ManagingExams\\"), TAKING_EXAMS("src\\Files\\files\\TakingExam\\"), EXAMINING("src\\Files\\files\\Examining\\"), ARCHIVES("\"src\\\\Files\\\\files\\\\Archives\\\\\"");
    private String address;

    ExamState(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

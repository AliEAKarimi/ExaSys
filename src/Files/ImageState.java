package Files;

public enum ImageState {
    MANAGER_PROFILE_IMAGE("src/Files/files/ManagerProfileImage/"), STUDENT_PROFILE_IMAGE("src/Files/files/StudentProfileImage/");
    private String address;

    ImageState(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

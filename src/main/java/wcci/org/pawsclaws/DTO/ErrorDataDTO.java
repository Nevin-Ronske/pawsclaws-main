package wcci.org.pawsclaws.DTO;

public class ErrorDataDTO {
    private String errorMessage; // Message describing the error
    private int errorCode = 0; // Code representing the error type (default is 0)

    // Getter for error message
    public String getErrorMessage() {
        return errorMessage;
    }

    // Setter for error message
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Getter for error code
    public int getErrorCode() {
        return errorCode;
    }

    // Setter for error code
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

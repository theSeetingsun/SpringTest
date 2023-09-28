package App.dto;



/**
 * dto:data transfer object 数据传输对象
 */

public class LoginDto {
    private String username;
    private String password;

    public LoginDto() {
    }


    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "LoginDto{username = " + username + ", password = " + password + "}";
    }
}

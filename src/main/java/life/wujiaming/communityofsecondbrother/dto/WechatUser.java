package life.wujiaming.communityofsecondbrother.dto;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 03/08/2023 15:11
 */
public class WechatUser {
    private String name;
    private Long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

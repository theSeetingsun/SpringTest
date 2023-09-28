package App.query;

public class ImageQuery extends BaseQuery{
    private String imagetype;
    private Integer imagestate;

    public ImageQuery() {
    }

    public ImageQuery(String imagetype, Integer imagestate) {
        this.imagetype = imagetype;
        this.imagestate = imagestate;
    }

    /**
     * 获取
     * @return imagetype
     */
    public String getImagetype() {
        return imagetype;
    }

    /**
     * 设置
     * @param imagetype
     */
    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

    /**
     * 获取
     * @return imagestate
     */
    public Integer getImagestate() {
        return imagestate;
    }

    /**
     * 设置
     * @param imagestate
     */
    public void setImagestate(Integer imagestate) {
        this.imagestate = imagestate;
    }

    public String toString() {
        return "ImageQuery{imagetype = " + imagetype + ", imagestate = " + imagestate + "}";
    }
}

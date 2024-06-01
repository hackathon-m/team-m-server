package hackerthon.demo.domain.enums;

public enum Category {
    ALL("null"),
    FOOD("https://image-model-demo.s3.ap-northeast-2.amazonaws.com/upload/47c964ec-7283-4f03-bcc7-7c7d59c9136d")
    ,CAFE("https://image-model-demo.s3.ap-northeast-2.amazonaws.com/upload/56337f97-a512-4bb3-bddc-d183240f4b66")
    ,MAKEUP("https://image-model-demo.s3.ap-northeast-2.amazonaws.com/upload/ffe0dde0-48d5-437c-a11e-e60496cbbc55")
    , ETC("https://image-model-demo.s3.ap-northeast-2.amazonaws.com/upload/b6c253d2-d60a-46a6-b2c4-3332b1c77e1b");

    private final String imageUrl;

    Category(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

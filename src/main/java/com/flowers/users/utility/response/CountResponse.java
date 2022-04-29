package com.flowers.users.utility.response;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 4/26/22
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountResponse {
    private Long count;

    public CountResponse(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

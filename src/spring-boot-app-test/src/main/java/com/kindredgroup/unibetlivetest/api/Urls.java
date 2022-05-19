package com.kindredgroup.unibetlivetest.api;

public  interface Urls {

    String basePath = "/api/v1";

    /** events apis **/
    String events = "/events";
    String selections = events + "/{id}/selections";

    /** bets api **/
    String bets = "bets";
    String addBet = bets + "/add";


    /** customers apis **/
    String customers = "/customers";
    String currentCustomer = customers + "/current";

}

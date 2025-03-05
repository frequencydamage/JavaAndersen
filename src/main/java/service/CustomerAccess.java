package service;

interface CustomerAccess {

    void listAvailableWorkspaces();

    void makeReservation();

    void cancelReservation();

    void listMyReservations();
}

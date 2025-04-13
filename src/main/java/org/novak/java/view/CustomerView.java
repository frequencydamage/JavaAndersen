package org.novak.java.view;

import org.novak.java.controller.CustomerController;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerView {

    private final CustomerController customerController;

    @Autowired
    public CustomerView(CustomerController customerController) {
        this.customerController = customerController;
    }

    @GetMapping
    public String showCustomerView() {
        return "customerView";
    }

    @GetMapping("/makeReservation")
    public String showMakeReservationForm(Model model) {
        List<Workspace> availableWorkspaces = customerController.listAvailableWorkspaces();
        if (availableWorkspaces.isEmpty()) {
            model.addAttribute("message", "No available workspaces!");
            return "customerView";
        }
        model.addAttribute("workspaces", availableWorkspaces);
        return "makeReservationView";
    }

    @PostMapping("/makeReservation")
    public String makeReservation(@RequestParam("workspaceId") int workspaceId, Model model) {
        try {
            customerController.makeReservation(workspaceId);
            model.addAttribute("message", "Reservation was created successfully!");
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("message", ex.getMessage());
        }
        return "customerView";
    }

    @GetMapping("/cancelReservation")
    public String showCancelReservationForm(Model model) {
        List<Reservation> myReservations = customerController.listMyReservations();
        if (myReservations.isEmpty()) {
            model.addAttribute("message", "No reservations to cancel!");
            return "customerView";
        }
        model.addAttribute("reservations", myReservations);
        return "cancelReservationView";
    }

    @PostMapping("/cancelReservation")
    public String cancelReservation(@RequestParam("reservationId") int reservationId, Model model) {
        try {
            customerController.cancelReservation(reservationId);
            model.addAttribute("message", "Reservation was successfully canceled!");
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("message", ex.getMessage());
        }
        return "customerView";
    }

    @GetMapping("/listAvailableWorkspaces")
    public String listAvailableWorkspaces(Model model) {
        List<Workspace> availableWorkspaces = customerController.listAvailableWorkspaces();
        if (availableWorkspaces.isEmpty()) {
            model.addAttribute("message", "No available workspaces!");
        } else {
            model.addAttribute("workspaces", availableWorkspaces);
        }
        return "listAvailableWorkspacesView";
    }

    @GetMapping("/listMyReservations")
    public String listMyReservations(Model model) {
        List<Reservation> myReservations = customerController.listMyReservations();
        if (myReservations.isEmpty()) {
            model.addAttribute("message", "No reservations to list!");
        } else {
            model.addAttribute("reservations", myReservations);
        }
        return "listMyReservationsView";
    }
}
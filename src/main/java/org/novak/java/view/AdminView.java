package org.novak.java.view;

import org.novak.java.controller.AdminController;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.reservation.Reservation;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminView {

    private AdminController adminController;

    @Autowired
    public AdminView(AdminController adminController) {
        this.adminController = adminController;
    }

    @GetMapping
    public String showAdminView() {
        return "adminView";
    }

    @GetMapping("/addWorkspace")
    public String showAddWorkspaceForm() {
        return "addWorkspaceView";
    }

    @PostMapping("/addWorkspace")
    public String addWorkspace(@RequestParam("price") double price,
                               @RequestParam("workspaceType") int workspaceType,
                               Model model) {
        if (price > 0.00 && price < 1500.00) {
            price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
            WorkspaceType type = WorkspaceType.values()[workspaceType - 1];
            adminController.addWorkspace(price, type);

            model.addAttribute("message", "Workspace successfully added!");

            return "adminView";
        } else {
            model.addAttribute("message", "Price must be between 0.01 and 1499.99");

            return "addWorkspaceView";
        }
    }

    @GetMapping("/removeWorkspace")
    public String showRemoveWorkspaceForm(Model model) {
        List<Workspace> workspaces = adminController.listWorkspaces();
        if (workspaces.isEmpty()) {
            model.addAttribute("message", "No workspaces to remove.");
        } else {
            model.addAttribute("workspaces", workspaces);
        }

        return "removeWorkspaceView";
    }

    @PostMapping("/removeWorkspace")
    public String removeWorkspace(@RequestParam("workspaceId") int workspaceId, Model model) {
        try {
            adminController.removeWorkspace(workspaceId);
            model.addAttribute("message", "Workspace removed successfully.");
        } catch (ResourceNotFoundException e) {
            model.addAttribute("message", e.getMessage());
        }

        return "removeWorkspaceView";
    }

    @GetMapping("/listWorkspaces")
    public String listWorkspaces(Model model) {
        List<Workspace> workspaces = adminController.listWorkspaces();
        if (workspaces.isEmpty()) {
            model.addAttribute("message", "No workspaces to list.");
        } else {
            model.addAttribute("workspaces", workspaces);
        }

        return "listWorkspacesView";
    }

    @GetMapping("/listReservations")
    public String listReservations(Model model) {
        List<Reservation> reservations = adminController.listAllReservations();
        if (reservations.isEmpty()) {
            model.addAttribute("message", "No reservations to list.");
        } else {
            model.addAttribute("reservations", reservations);
        }

        return "listReservationsView";
    }
}
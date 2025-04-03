package org.novak.java.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.novak.java.BaseTest;
import org.novak.java.customException.ResourceNotFoundException;
import org.novak.java.model.workspace.Workspace;
import org.novak.java.model.workspace.WorkspaceRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.novak.java.model.workspace.WorkspaceType.*;

@ExtendWith(MockitoExtension.class)
public class WorkspaceServiceTest extends BaseTest {

    private final Double WORKSPACE_PRICE = 1234.56;
    private final Integer WORKSPACE_ID = 555;

    @Mock
    private WorkspaceRepository workspaceRepository;

    @InjectMocks
    private WorkspaceService workspaceService;

    @DisplayName("Creating a workspace")
    @Test
    void givenValidWorkspace_whenAdd_thenCreatedInRepository() {
        ArgumentCaptor<Workspace> workspaceCaptor = ArgumentCaptor.forClass(Workspace.class);

        workspaceService.addWorkspace(WORKSPACE_PRICE, OPEN_SPACE);

        verify(workspaceRepository).create(workspaceCaptor.capture());

        Workspace actualWorkspace = workspaceCaptor.getValue();
        Workspace expectedWorkspace = new Workspace(0, WORKSPACE_PRICE, OPEN_SPACE, true, null);

        assertThat(actualWorkspace.getId()).isGreaterThan(0);
        assertThat(actualWorkspace)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedWorkspace);
    }

    @DisplayName("Successfully removes an existing workspace by ID")
    @Test
    void givenExistingWorkspace_whenRemoveById_thenWorkspaceRemoved() {
        Workspace workspace = new Workspace(WORKSPACE_ID, WORKSPACE_PRICE, CABIN, true, null);
        given(workspaceRepository.getById(any(Integer.class))).willReturn(workspace);

        workspaceService.removeWorkspace(WORKSPACE_ID);

        verify(workspaceRepository).deleteById(WORKSPACE_ID);
    }

    @DisplayName("Throws exception when trying to remove a non-existing workspace by ID")
    @Test
    void givenNonExistingWorkspace_whenRemoveById_thenResourceNotFoundExceptionThrown() {
        given(workspaceRepository.getById(any(Integer.class))).willReturn(null);

        assertThrowsExactly(ResourceNotFoundException.class, () -> workspaceService.removeWorkspace(WORKSPACE_ID));
        verify(workspaceRepository, never()).deleteById(any(Integer.class));
    }

    @DisplayName("Listing existing workspaces")
    @Test
    void givenExistingWorkspaces_whenListAll_thenReturnsWorkspaces() {
        List<Workspace> expectedWorkspaces = List.of(
                new Workspace(1, 1499.99, OPEN_SPACE, true, null),
                new Workspace(999, 1.0, CABIN, false, null),
                new Workspace(657, 125.55, PRIVATE, true, null)
        );
        given(workspaceRepository.getAll()).willReturn(expectedWorkspaces);

        List<Workspace> actualWorkspaces = workspaceService.listAllWorkspaces();

        verify(workspaceRepository).getAll();
        assertThat(actualWorkspaces).containsExactlyInAnyOrderElementsOf(expectedWorkspaces);
    }

    @DisplayName("Listing no workspaces")
    @Test
    void givenNoWorkspaces_whenListAll_thenReturnsEmptyList() {
        given(workspaceRepository.getAll()).willReturn(Collections.emptyList());

        List<Workspace> actualWorkspaces = workspaceService.listAllWorkspaces();

        verify(workspaceRepository).getAll();
        assertThat(actualWorkspaces).isEmpty();
    }
}
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../constants';
import { Milestone } from '../model/milestone';
import { MilestoneStatus } from '../model/milestone-status';
import { Project } from '../model/project';
import { UserPermission } from '../model/user-permission';
import { UserProfile } from '../model/user-profile';
import { CoreModule } from './core.module';


@Injectable({ providedIn: CoreModule})
export class ProjectService {
    constructor(private _httpClient: HttpClient) { }
    
    getProjects(): Observable<Project[]> {
        return this._httpClient.get<Project[]>(Constants.apiRoot + 'Projects');
    }

    getProject(projectId: number): Observable<Project> {
        return this._httpClient.get<Project>(Constants.apiRoot + 'Projects/' + projectId);
    }

    getProjectUsers(projectId: number): Observable<UserProfile[]> {
        return this._httpClient.get<UserProfile[]>(Constants.apiRoot + 'Projects/' + projectId + '/Users');
    }

    addProject(project: Project): Observable<Project> {
        return this._httpClient.post<Project>(Constants.apiRoot + 'Projects', project);
    }

    deleteProject(project: Project): Observable<object> {
        return this._httpClient.delete(Constants.apiRoot + 'Projects/' + project.id);
    }

    addUserPermission(userPermission: UserPermission) {
        return this._httpClient.post(Constants.apiRoot + 'UserPermissions', userPermission);
    }

    removeUserPermission(userId: string, projectId: number) {
        return this._httpClient.delete(`${Constants.apiRoot}UserPermissions/?userId=${userId}&projectId=${projectId}`);
    }

    updateUserPermission(userPermission) {
        return this._httpClient.put(`${Constants.apiRoot}UserPermissions`, userPermission);
    }

    getMilestones(projectId: number): Observable<Milestone[]> {
        return this._httpClient.get<Milestone[]>(Constants.apiRoot + 'Milestone');
    }

    getMilestoneStatuses() {
        return this._httpClient.get<MilestoneStatus[]>(`${Constants.apiRoot}Projects/MilestoneStatuses`);
    }

    addMilestone(milestone: Milestone) {
        return this._httpClient.post(`${Constants.apiRoot}Projects/Milestones`, milestone);
    }

    deleteMilestone(id: number) {
        return this._httpClient.delete(`${Constants.apiRoot}Projects/Milestones/${id}`);
    }

    updateMilestone(milestone: Milestone) {
        return this._httpClient.put(`${Constants.apiRoot}Projects/Milestones/${milestone.id}`, milestone);
    }
}
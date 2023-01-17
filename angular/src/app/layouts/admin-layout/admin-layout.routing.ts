import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { CategoriesComponent } from '../../pages/categories/categories.component';
import { QuestionnairesComponent } from '../../pages/questionnaires/questionnaires.component';
import { ForumComponent } from '../../pages/forum/forum.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'user-profile', component: UserProfileComponent },
    { path: 'tables', component: TablesComponent },
    { path: 'icons', component: IconsComponent },
    { path: 'categories', component: CategoriesComponent },
    { path: 'questionnaires', component: QuestionnairesComponent },
    { path: 'forum', component: ForumComponent }
];
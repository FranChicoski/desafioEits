import { NgModule, ModuleWithProviders } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './views/categoria/form/form.component';
import { ListCategoriaComponent } from './views/categoria/list/list.component';
import { DetailCategoriaComponent } from './views/categoria/detail/detail.component';
import { CategoriaViewComponent } from './views/categoria/categoria-view.component';

const routes: Routes = [
	{
        path: '', redirectTo:'categoria',  pathMatch: 'full' 
    },
    {
        path:'categoria', component: CategoriaViewComponent ,
        children:
        [
            {path: '', component: ListCategoriaComponent },
            {path: 'cadastro', component: FormComponent },
            {path: 'editar/:categoriaId', component: FormComponent },
            {path: 'detalhe/:categoriaId', component: DetailCategoriaComponent }
        ]},
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });

/**
 *
 */
@NgModule({
    imports: [ routing ],//RouterModule.
    exports: [ RouterModule ]
})
export class RoutingModule
{

}

export const appRoutingProviders: any[] = [];

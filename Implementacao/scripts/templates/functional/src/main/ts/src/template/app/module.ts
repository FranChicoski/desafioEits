//===============================ANGULAR MODULES=================================
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import {
  MdAutocompleteModule,
  MdButtonModule,
  MdButtonToggleModule,
  MdCardModule,
  MdCheckboxModule,
  MdDatepickerModule,
  MdDialogModule,
  MdIconModule,
  MdInputModule,
  MdListModule,
  MdMenuModule,
  MdNativeDateModule,
  MdProgressSpinnerModule,
  MdRadioModule,
  MdSelectModule,
  MdSidenavModule,
  MdSlideToggleModule,
  MdSnackBarModule,
  MdToolbarModule,
  MdTooltipModule
} from '@angular/material';

import { FormsModule } from '@angular/forms';
import { Http } from '@angular/http';

import { Md2DatepickerModule } from 'md2/datepicker';
//===============================COVALENT MODULES================================
import {
  CovalentChipsModule,
  CovalentCommonModule,
  CovalentDataTableModule,
  CovalentDialogsModule,
  CovalentExpansionPanelModule,
  CovalentFileModule,
  CovalentLayoutModule,
  CovalentLoadingModule,
  CovalentMediaModule,
  CovalentMessageModule,
  CovalentPagingModule,
  CovalentStepsModule,
  TdDialogService,
  TdLayoutComponent,
} from '@covalent/core';
//===============================APP MODULES=====================================
import { appRoutingProviders, RoutingModule } from './routing.module';
import 'rxjs/add/observable/throw';
//==============================APP SERVICES=====================================
//==============================APP COMPONENTS===================================

//=============================APP DIRECTIVES====================================

//===============================APP MODELS======================================

/**
 *
 */
@NgModule({
  declarations: [
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    CovalentLayoutModule,
    CovalentStepsModule,
    CovalentChipsModule,
    CovalentFileModule,
    CovalentExpansionPanelModule,
    CovalentPagingModule,
    CovalentLoadingModule,
    CovalentMediaModule,
    CovalentMessageModule,
    CovalentCommonModule,
    CovalentDataTableModule,
    CovalentDialogsModule,
    MdAutocompleteModule,
    MdIconModule,
    MdSelectModule,
    MdSlideToggleModule,
    MdMenuModule,
    MdInputModule,
    MdCheckboxModule,
    MdRadioModule,
    MdSidenavModule,
    MdSnackBarModule,
    MdDialogModule,
    MdCardModule,
    MdButtonModule,
    MdToolbarModule,
    MdListModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdTooltipModule,
    MdProgressSpinnerModule,
    MdButtonToggleModule,
    FormsModule,
    RoutingModule,
    Md2DatepickerModule
  ],
  exports: [
    BrowserModule
  ],
  providers: [
    appRoutingProviders,
    TdLayoutComponent,
    TdDialogService,
  ],
  bootstrap: [],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class Module {

}

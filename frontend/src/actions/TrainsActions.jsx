import {createAction} from '@reduxjs/toolkit'

export const SET_TRAINS = 'SET_TRAINS';
export const SET_PAGE = 'SET_PAGE';
export const SET_FILTER = 'SET_FILTER';

export const setTrains = createAction(SET_TRAINS);
export const setPage = createAction(SET_PAGE);
export const setFilter = createAction(SET_FILTER);

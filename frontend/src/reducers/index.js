import {combineReducers} from "redux";
import trainsReducer from './TrainsReducer';
import schedulesReducer from './SchedulesReducer';

export default combineReducers({
    trains: trainsReducer,
    schedules: schedulesReducer
});

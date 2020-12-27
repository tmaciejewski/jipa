import {SET_SCHEDULES} from '../actions/SchedulesActions';

export default function schedulesReducer(state = {train_name: null}, action) {
    switch (action.type) {
        case SET_SCHEDULES:
            return action.payload;
        default:
            return state;
    }
}

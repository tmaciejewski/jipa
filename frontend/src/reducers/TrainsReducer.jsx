import {SET_TRAINS, SET_PAGE, SET_FILTER} from '../actions/TrainsActions';

const initialState = {
    allTrains: null,
    pageNumber: 1,
    pageSize: 100,
    pageCount: null,
    filter: '',
    filteredTrains: [],
    trainsOnPage: []
};

export default function trainsReducer(state = initialState, action) {
    switch (action.type) {
        case SET_TRAINS: {
            const allTrains = action.payload;
            const pageCount = Math.ceil(allTrains.length / state.pageSize);

            return {
                allTrains: allTrains,
                pageNumber: 1,
                pageSize: state.pageSize,
                pageCount: pageCount,
                filter: '',
                filteredTrains: allTrains,
                trainsOnPage: getPage(allTrains, 1, state.pageSize)
            };
        }
        case SET_PAGE: {
            const pageNumber = action.payload;
            return {
                ...state,
                pageNumber: pageNumber,
                trainsOnPage: getPage(state.filteredTrains, pageNumber, state.pageSize)
            }
        }
        case SET_FILTER: {
            const filter = action.payload;
            const filteredTrains = state.allTrains.filter(train => trainMatchesFilter(train, filter));
            const pageCount = Math.ceil(filteredTrains.length / state.pageSize);

            return {
                ...state,
                filter: filter,
                filteredTrains: filteredTrains,
                pageNumber: 1,
                pageCount: pageCount,
                trainsOnPage: getPage(filteredTrains, 1, state.pageSize)
            }
        }
        default:
            return state;
    }
}

function getPage(trains, page, pageSize) {
    return trains.slice((page - 1) * pageSize, page * pageSize);
}

function trainMatchesFilter(train, filter) {
    const matchesFilter = (value, filter) => value.toLowerCase().indexOf(filter.toLowerCase()) >= 0;

    return matchesFilter(train.train_name, filter)
        || train.stations.some(station => matchesFilter(station, filter));
};

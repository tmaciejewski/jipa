import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import MainPage from './main/MainPage';
import TrainDetails from './train/TrainDetails';
import './App.css';

export default function App({trains, setTrains}) {
    return (
        <Router>
            <Switch>
                <Route path='/train/:train_name+'>
                    <TrainDetails/>
                </Route>
                <Route path='/'>
                    <MainPage/>
                </Route>
            </Switch>
        </Router>
    );
}

import {useEffect, Fragment} from 'react';
import {useParams, Link} from 'react-router-dom';
import {connect} from 'react-redux';
import axios from 'axios';
import {setSchedules} from '../../actions/SchedulesActions';
import StationRow from './StationRow';
import TimeRow from './TimeRow';

function TrainDetails({details, setSchedules}) {
    const {train_name} = useParams();

    useEffect(() => {
        if (details.train_name !== train_name) {
            axios.get(`http://ipa.lovethosetrains.com/api/trains/${train_name}`)
                .then(response => setSchedules(response.data));
        }
    }, [details, train_name, setSchedules]);

    return (
        <div>
            <h1><div><Link to='/'>&lt;&lt;</Link></div>{train_name}</h1>
            {details.train_name !== train_name && <img alt='Wczytywanie' src={process.env.PUBLIC_URL + '/loader.gif'} />}
            <table>
                <tbody>

                    {details.train_name === train_name && details.schedules.map(schedule =>
                        <Fragment key={schedule.schedule_id}>
                            <StationRow schedule={schedule}/>
                            <TimeRow schedule={schedule}/>
                        </Fragment>)}
                </tbody>
            </table>
        </div>
    );
}

export default connect(state => ({details: state.schedules}), {setSchedules})(TrainDetails);

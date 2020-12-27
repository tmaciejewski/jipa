import {useEffect, Fragment} from 'react';
import {connect} from 'react-redux';
import axios from 'axios';
import {setTrains, setPage, setFilter} from '../../actions/TrainsActions';
import PageSwitcher from './PageSwitcher';
import TrainList from './TrainList';

function MainPage({trainsState, setTrains, setPage, setFilter}) {
    const {allTrains, trainsOnPage, pageNumber, pageCount, filter} = trainsState;

    useEffect(() => {
        if (!allTrains) {
            axios.get('http://ipa.lovethosetrains.com/api/trains').then(response => setTrains(response.data.trains));
        }
    }, [allTrains, setTrains]);

    const renderContent = () => {
        if (!allTrains) {
            return (<img alt='Wczytywanie' src={process.env.PUBLIC_URL + '/loader.gif'} />);
        } else if (trainsOnPage.length === 0) {
            return (<p>Nie znaleziono pociągów</p>);
        } else {
            return (
                <Fragment>
                    <PageSwitcher page={pageNumber} pageCount={pageCount} onPageChanged={setPage}/>
                    <TrainList trains={trainsOnPage}/>
                </Fragment>
            );
        }
    };

    return (
        <div>
            <h1>InfoPasażer Archiver</h1>
            <h5>Poprzednie lata: <a href="ipa_15_16.tar.bz2">2015/2016</a> | <a href="ipa_16_17.7z">2016/2017</a> | <a href="ipa_17_18.7z">2017/2018</a> | <a href="ipa_18_19.7z">2018/2019</a> | <a href="ipa_19_20.7z">2019/2020</a></h5>
            <label htmlFor="filter">Filtruj pociąg/stację:</label><input id="filter" value={filter} onChange={e => setFilter(e.target.value)}/>
            <div id='main-content'>
                {renderContent()}
            </div>
        </div>
    );
}

export default connect(state => ({trainsState: state.trains}), {setTrains, setPage, setFilter})(MainPage);

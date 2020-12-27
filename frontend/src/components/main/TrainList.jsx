import TrainElement from './TrainElement';

export default function TrainList({trains}) {
    return (
        <table id='main-table'>
            <tbody>
                <tr><th>Numer pociągu</th><th>Skąd</th><th>Przez</th><th>Dokąd</th></tr>
                {trains.map(train => <TrainElement key={train.train_name} train={train}/>)}
            </tbody>
        </table>
    );
}

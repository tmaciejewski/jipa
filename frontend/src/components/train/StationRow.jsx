import {Fragment} from 'react';

export default function StationRow({schedule}) {
    const dateFreq = 10;

    return (
        <tr>
            {schedule.info.map((info, index) =>
                <Fragment key={info.station_name}>
                    {index % dateFreq === 0 && <th className='date' rowSpan={2}>{schedule.schedule_date}</th>}
                    <th>{info.station_name}</th>
                </Fragment>
            )}
        </tr>
    );
}

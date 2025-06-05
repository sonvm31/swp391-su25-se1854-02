import { useRouteError } from "react-router-dom";
import { Button, Result } from 'antd';

const Errors = () => {
    const error = useRouteError();
    return (

        <Result
            status="500"
            title="Oops! Something went wrong."
            subTitle={error.message}
        />

    );
}

export default Errors
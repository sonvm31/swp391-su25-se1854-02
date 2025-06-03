import { useRouteError } from "react-router-dom";

const Errors = () => {
    const error = useRouteError();
    return (
        <div>
            <h2>Oops! Something went wrong.</h2>
            <p>{error.message}</p>
        </div>
    );
}

export default Errors
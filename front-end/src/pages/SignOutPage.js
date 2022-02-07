import React, { useContext } from 'react';
import UserContext from '../UserContext';
import Layout from "../components/Layout";



const SignOut = ()=>{
    const{setUser} = useContext(UserContext);
    return(
        <Layout>
             <div>
            <h2>
                Thanks for using the app. See you again.
            </h2>

        </div>
        </Layout>
       
    )
}

export default SignOut;
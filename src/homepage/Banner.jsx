import React from "react";

function Banner(){
    return(
        <div className="p-0  bm-4 bg-light">
            <div className="container-fluid py-5 \
            text-white d-flex justify-content-center align-items-center"> 
                <img src={require ('../images/Bannerimg/Banner.png')} alt="Banner" className="img-fluid" />
            </div>

        </div>
    )
}

export default Banner;
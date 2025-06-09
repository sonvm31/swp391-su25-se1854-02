import React from 'react';
import '../../styles/doctor/layout.css';

const DoctorHeader = () => {
  return (
    <header className="doctor-header">
      <div className="welcome-section">
        <h1>Chào mừng, Bác sĩ</h1>
        <p>Khoa HIV/AIDS</p>
      </div>
      <div className="profile-section">
        {/* Profile avatar and dropdown will go here */}
        <button className="profile-btn">Bác sĩ</button>
      </div>
    </header>
  );
};

export default DoctorHeader;

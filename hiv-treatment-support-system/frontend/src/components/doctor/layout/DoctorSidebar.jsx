import React from 'react';
import { Link } from 'react-router-dom';
import '../../../styles/doctor/layout.css';
import appLogo from '../../../assets/appLogo.png';

const DoctorSidebar = () => {
  return (
    <aside className="doctor-sidebar">
      <div className="logo">
        <img src={appLogo} alt="Doctor Panel" />
        <h2>HIV Care Center</h2>
      </div>
      
      <nav className="sidebar-nav">
        <ul>
          <li>
            <Link to="/doctor/schedule">
              <i className="calendar-icon"></i>
              Lịch làm việc
            </Link>
          </li>
          <li>
            <Link to="/doctor/patients">
              <i className="patient-icon"></i>
              Hồ sơ bệnh nhân
            </Link>
          </li>
          <li>
            <Link to="/doctor/test-results">
              <i className="test-icon"></i>
              Kết quả xét nghiệm
            </Link>
          </li>
          <li>
            <Link to="/doctor/regimens">
              <i className="regimen-icon"></i>
              Phác đồ điều trị
            </Link>
          </li>
        </ul>
      </nav>

      <button className="logout-btn">
        Đăng xuất
      </button>
    </aside>
  );
};

export default DoctorSidebar;

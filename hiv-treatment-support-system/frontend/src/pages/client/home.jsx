
import { Layout, theme } from 'antd';
import AppHeader from '../../components/layouts/client/app-header';
import AppFooter from '../../components/layouts/client/app-footer';
import CareIntroSection from '../../components/home-section/CareIntroSection';
import ServicesSection from '../../components/home-section/ServicesSection';
import FullServicesSection2 from '../../components/home-section/FullServicesSection2';
import DoctorList from '../../components/home-section/DoctorList';
import Document from '../../components/home-section/Document';
const { Content } = Layout;

const Home = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout>
            <AppHeader />
            <Content style={{ padding: '15px' }}>
                <div
                    style={{
                        background: colorBgContainer,
                        minHeight: 1080,
                        padding: 24,
                        borderRadius: borderRadiusLG,
                    }}
                >
                    <CareIntroSection />
                    <ServicesSection />
                    <FullServicesSection2/>
                    <DoctorList/>
                    <Document/>
                </div>
            </Content>
            <AppFooter />
        </Layout>
    );
};
export default Home;
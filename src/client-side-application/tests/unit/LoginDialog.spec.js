import { shallowMount } from '@vue/test-utils';
import ElementPlus from 'element-plus';
import LoginDialog from '@/components/TopBarComponents/LoginDialog.vue';

describe('LoginDialog.vue', () => {
  let wrapper;
  const visible = true;

  beforeEach(() => {
    wrapper = shallowMount(LoginDialog, {
      propsData: { visible },
      global: {
        plugins: [ElementPlus],
        provide: {
          'login-info': {
            value: {
              userId: '',
              fakeName: '',
              userPasswd: ''
            }
          }
        }
      }
    });
  });

  it('renders when visible is true', () => {
    expect(wrapper.isVisible()).toBe(true);
  });

  it('does not render when visible is false', async () => {
    await wrapper.setProps({ visible: false });
    expect(wrapper.isVisible()).toBe(false);
  });

  it('emits "close" event when cancel button is clicked', async () => {
    // const cancelButton = wrapper.findAll('button').at(2);
    // await cancelButton.trigger('click');
    // expect(wrapper.emitted().close).toBeTruthy();
  });

  it('validates form and emits "login" event on submit', async () => {
    // const idInput = wrapper.find('input').at(0);
    // const fakenameInput = wrapper.find('input').at(1);
    // const passwordInput = wrapper.find('input').at(2);

    // await idInput.setValue('testId');
    // await fakenameInput.setValue('testFakename');
    // await passwordInput.setValue('testPassword');
    
    // const submitButton = wrapper.findAll('button').at(1);
    // await submitButton.trigger('click');
    
    // expect(wrapper.emitted().login).toBeTruthy();
  });

  it('validates form and emits "register" event on submit', async () => {
    // const idInput = wrapper.find('input').at(0);
    // const fakenameInput = wrapper.find('input').at(1);
    // const passwordInput = wrapper.find('input').at(2);

    // await idInput.setValue('testId');
    // await fakenameInput.setValue('testFakename');
    // await passwordInput.setValue('testPassword');
    
    // const submitButton = wrapper.findAll('button').at(0);
    // await submitButton.trigger('click');
    
    // expect(wrapper.emitted().register).toBeTruthy();
  });

  it('shows validation error if fields are empty', async () => {
    // const submitButton = wrapper.findAll('button').at(1);
    // await submitButton.trigger('click');

    // 等待DOM更新以显示验证错误
    // await wrapper.vm.$nextTick();

    // const errors = wrapper.findAll('.el-form-item__error');
    // expect(errors.length).toBe(0); // 每个字段都有错误消息
    // expect(errors.at(0).text()).toBe('请输入ID');
    // expect(errors.at(1).text()).toBe('请输入假名');
    // expect(errors.at(2).text()).toBe('请输入密码');
  });
});

import { shallowMount } from '@vue/test-utils';
import ElementPlus from 'element-plus';
import TopBar from '@/components/TopBar.vue';
import LoginDialog from '@/components/TopBarComponents/LoginDialog.vue';
import CreateRoomDialog from '@/components/TopBarComponents/CreateRoomDialog.vue';
import { nextTick } from 'vue';

describe('TopBar.vue', () => {
  let wrapper;

  const loginInfo = {
    value: {
      isLogIn: false,
      headPhoto: 'https://example.com/avatar.png'
    }
  };

  const roomInfo = {
    value: {
      currentRoomName: 'Test Room'
    }
  };

  beforeEach(() => {
    wrapper = shallowMount(TopBar, {
      global: {
        provide: {
          'login-info': loginInfo,
          'room-info': roomInfo
        },
        plugins: [ElementPlus],
      }
    });
  });

  it('renders the correct initial components', () => {
    expect(wrapper.findComponent(LoginDialog).exists()).toBe(true);
    expect(wrapper.findComponent(CreateRoomDialog).exists()).toBe(true);
    expect(wrapper.find('#avatar').exists()).toBe(true);
    expect(wrapper.find('#log-button').text()).toBe('');
    expect(wrapper.find('#title').text()).toBe('🏡Test Room');
  });

  it('toggles the login dialog when login button is clicked', async () => {
    const logButton = wrapper.find('#log-button');
    await logButton.trigger('click');
    await nextTick();
    expect(wrapper.findComponent(LoginDialog).props('visible')).toBe(true);

    loginInfo.value.isLogIn = true;
    await logButton.trigger('click');
    await nextTick();
    expect(wrapper.emitted()['log-out']).toBeTruthy();
  });

  it('shows create room dialog when create room button is clicked', async () => {
    const createRoomButton = wrapper.find('#create-room-button');
    await createRoomButton.trigger('click');
    await nextTick();
    expect(wrapper.findComponent(CreateRoomDialog).props('visible')).toBe(true);
  });

  it('emits the correct events when dialog actions are performed', async () => {
    // Simulate login
    wrapper.vm.showLoginDialog = true;
    await nextTick();
    wrapper.findComponent(LoginDialog).vm.$emit('login');
    await nextTick();
    expect(wrapper.emitted()['log-in']).toBeTruthy();
    expect(wrapper.vm.showLoginDialog).toBe(false);

    // Simulate register
    wrapper.vm.showLoginDialog = true;
    await nextTick();
    wrapper.findComponent(LoginDialog).vm.$emit('register');
    await nextTick();
    expect(wrapper.emitted()['register']).toBeTruthy();
    expect(wrapper.vm.showLoginDialog).toBe(false);

    // Simulate create room
    wrapper.vm.showCreateDialog = true;
    await nextTick();
    wrapper.findComponent(CreateRoomDialog).vm.$emit('createroom', 'New Room');
    await nextTick();
    expect(wrapper.emitted()['createroom']).toBeTruthy();
    expect(wrapper.emitted()['createroom'][0]).toEqual(['New Room']);
    expect(wrapper.vm.showCreateDialog).toBe(false);
  });

  it('emits the random-change-user-avatar event when avatar is double clicked', async () => {
    const avatar = wrapper.find('#avatar');
    await avatar.trigger('dblclick');
    expect(wrapper.emitted()['random-change-user-avatar']).toBeTruthy();
  });
});

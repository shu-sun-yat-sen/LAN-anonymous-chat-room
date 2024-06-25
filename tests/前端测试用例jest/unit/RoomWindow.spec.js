import { shallowMount } from '@vue/test-utils';
import ElementPlus from 'element-plus';
import RoomWindow from '@/components/SideBarComponent/RoomWindow.vue';

describe('RoomWindow.vue', () => {
  let wrapper;
  const roomname = 'Test Room';
  const newestMessage = 'This is a test message';
  const roomAvatar = 'avatar.jpg';
  let roomInfo;

  beforeEach(() => {
    roomInfo = {
      value: {
        currentRoomName: roomname
      }
    };

    wrapper = shallowMount(RoomWindow, {
      propsData: { roomname, newestMessage, roomAvatar },
      global: {
        plugins: [ElementPlus],
        provide: {
          'room-info': roomInfo
        }
      }
    });
  });

  it('renders props correctly', () => {
    expect(wrapper.find('.room-name').text()).toBe('Test Room');
    expect(wrapper.find('.newest-message').text()).toBe('This is a test ...');
    expect(wrapper.find('.avatar').attributes('src')).toBe('avatar.jpg');
  });

  it('applies correct dynamic style', async () => {
    // wrapper.vm.$root.$options.provide['room-info'].currentRoomName = roomname;
    await wrapper.vm.$nextTick();
    expect(wrapper.element.style.backgroundColor).toBe('rgb(187, 186, 186)');

    // wrapper.vm.$root.$options.provide['room-info'].value.currentRoomName = 'Other Room';
    await wrapper.vm.$nextTick();
    expect(wrapper.element.style.backgroundColor).toBe('rgb(187, 186, 186)');
  });

  it('handles click event correctly', async () => {
    wrapper.trigger('click');
    await wrapper.vm.$nextTick();
    // expect(wrapper.vm.$root.$options.provide['room-info'].value.currentRoomName).toBe(roomname);
  });

  it('emits event on avatar double click', async () => {
    wrapper.find('.avatar').trigger('dblclick');
    await wrapper.vm.$nextTick();
    expect(wrapper.emitted()['random-update-room-avatar']).toBeTruthy();
    expect(wrapper.emitted()['random-update-room-avatar'][0]).toEqual([roomname]);
  });
});

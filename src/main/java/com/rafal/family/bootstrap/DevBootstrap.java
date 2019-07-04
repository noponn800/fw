package com.rafal.family.bootstrap;

import com.rafal.family.model.ApplicationUser;
import com.rafal.family.repositories.GroupRepository;
import com.rafal.family.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.rafal.family.model.ApplicationGroup;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public DevBootstrap(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
    }

    private void initData()
    {
        ApplicationUser user = new ApplicationUser("Jarosław", "Główka","nopon", "szczupak");
        ApplicationGroup group = new ApplicationGroup("Rodzina Główków");

        userRepository.save(user);
        groupRepository.save(group);

        user.addGroup(group);

        userRepository.save(user);
        groupRepository.save(group);

    }
}
